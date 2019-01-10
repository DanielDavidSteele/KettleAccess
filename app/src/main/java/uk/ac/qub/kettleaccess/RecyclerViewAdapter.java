package uk.ac.qub.kettleaccess;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Bean> mData;
    Dialog myDialog;
    Fragment mFragment;
    private String[] urls;

    public RecyclerViewAdapter(Context mContext, List<Bean> mData, Fragment fragment) {
        this.mContext = mContext;
        this.mData = mData;
        this.mFragment = fragment;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_bean, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        //Dialog ini

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_bean);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        vHolder.item_bean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialog_name_tv = (TextView) myDialog.findViewById(R.id.dialog_name);
                TextView dialog_origin_tv = (TextView) myDialog.findViewById(R.id.dialog_origin);
                TextView dialog_desc_tv = (TextView) myDialog.findViewById(R.id.dialog_desc);
                final ImageView dialog_bean_img = (ImageView) myDialog.findViewById(R.id.dialog_img);
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                if(mData.get(vHolder.getAdapterPosition()).getOrigins().length ==1){
                    dialog_origin_tv.setText(mData.get(vHolder.getAdapterPosition()).getOrigin());
                }
                else if(mData.get(vHolder.getAdapterPosition()).getOrigins().length > 1){
                    String[] origins;
                    origins = mData.get(vHolder.getAdapterPosition()).getOrigins();
                    String originString = "";
                    int originIndex = 0;
                    for (String origin : origins){
                        originString += origin + ((++originIndex < origins.length)?", ":"");
                    }
                    dialog_origin_tv.setText(originString);
                }

                if(mData.get(vHolder.getAdapterPosition()).getOrigins().length == 1) {
                    new DownloadImageTask(dialog_bean_img)
                            .execute(Dictionary.Url + "img/"+mData.get(vHolder.getAdapterPosition()).getOrigin().toLowerCase().replace(" ","")+".png");
                }
                else if(mData.get(vHolder.getAdapterPosition()).getOrigins().length > 1) {


                            new MergeDownload(dialog_bean_img, mData.get(vHolder.getAdapterPosition()).getOrigins())
                                    .execute("");

                }


                dialog_desc_tv.setText(mData.get(vHolder.getAdapterPosition()).getDescription());
                Button useBean = (Button) myDialog.findViewById(R.id.dialog_btn);
                useBean.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainActivity)mFragment.getActivity()).test = mData.get(vHolder.getAdapterPosition()).getTemp();
                        ((MainActivity)mFragment.getActivity()).useTemp = true;
                        Log.d("DEBUG", "Test = "+((MainActivity)mFragment.getActivity()).test);
                        HomeFragment frag = new HomeFragment();
                        ((MainActivity)mFragment.getActivity()).loadFragment(frag);
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });




        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final Bean bean = mData.get(position);


        holder.tv_bean.setText(bean.getName());
        if (bean.getOrigins().length ==1){
            holder.tv_origin.setText(bean.getOrigin());
        }
        else if (bean.getOrigins().length > 1){
            String[] origins;
            origins = bean.getOrigins();
            String originString = "";
            int originIndex = 0;
            for (String origin : origins){
                originString += origin + ((++originIndex < origins.length)?", ":"");
            }
            holder.tv_origin.setText(originString);
        }

        holder.tv_temp.setText(String.valueOf(bean.getTemp()+"°C"));
        if(bean.getOrigins().length==1) {
            new DownloadImageTask(holder.img)
                    .execute(Dictionary.Url + "img/" +bean.getOrigins()[0].toLowerCase().replace(" ","")+".png");
        }
        else if(bean.getOrigins().length > 1) {
            MergeThread mt = new MergeThread(holder) {
                public void run() {
                    new MergeDownload(holder.img, bean.getOrigins())
                            .execute("");
                }
            };
            mt.start();


        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_bean;
        private TextView tv_bean;
        private TextView tv_origin;
        private TextView tv_desc;
        private TextView tv_temp;
        private ImageView img;


        public MyViewHolder(View itemView){
            super(itemView);
            item_bean = (LinearLayout) itemView.findViewById(R.id.bean_item);
            tv_bean = (TextView) itemView.findViewById(R.id.name_bean);
            tv_origin = (TextView) itemView.findViewById(R.id.origin_bean);
            tv_desc = (TextView) itemView.findViewById(R.id.dialog_desc);
            tv_temp = (TextView) itemView.findViewById(R.id.temp_bean);
            img = (ImageView) itemView.findViewById(R.id.img_bean);

        }

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private class MergeDownload extends AsyncTask<String, Void, Bitmap> {

        ArrayList<Bitmap> sourceImages = new ArrayList<>();
        String[] origins;
        ImageView bmImage;
        int width = 150, height = 150;

        public MergeDownload(ImageView bmImage, String[] origins) {
            this.bmImage = bmImage;
            this.origins = origins;
        }

        protected Bitmap doInBackground(String[] urls) {
            Bitmap image = null;
            try {
                for(String origin : origins) {
                    InputStream in = new java.net.URL(Dictionary.Url + "img/" + origin.toLowerCase() + ".png").openStream();
                    Bitmap originImg = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(in), width, height, false);
                    if(originImg!=null) sourceImages.add(originImg);
                    in.close();
                }
                image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                for(int pixel = 0; pixel < (width * height); pixel++) {
                    int y = (pixel/width);
                    int x = pixel % width;
                    int originIndex = (int)((((float)pixel/(float)width) - (pixel/width)) * sourceImages.size());
                    Bitmap originImg = sourceImages.get(originIndex);
                    image.setPixel(x,y,originImg.getPixel(x,y));
                }
            } catch(IOException e) {
                System.out.println(e);
            }
            System.out.println("Returning merged image");


            final Bitmap finalImage = image;
            new Thread(){
                public void run() {
                    ((MainActivity)mFragment.getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bmImage.setImageBitmap(finalImage);
                        }
                    });
                }
            }.start();

                
            

            return finalImage;
        }

        protected void OnPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }



}
