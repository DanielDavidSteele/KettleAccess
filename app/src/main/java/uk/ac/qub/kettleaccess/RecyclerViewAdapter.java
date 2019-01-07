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

import java.io.InputStream;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Bean> mData;
    Dialog myDialog;
    Fragment mFragment;

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
                ImageView dialog_bean_img = (ImageView) myDialog.findViewById(R.id.dialog_img);
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialog_origin_tv.setText(mData.get(vHolder.getAdapterPosition()).getOrigin());
                new DownloadImageTask(dialog_bean_img)
                        .execute("http://10.6.1.89/img/"+mData.get(vHolder.getAdapterPosition()).getImage()+".png");

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
        holder.tv_origin.setText(bean.getOrigin());
        holder.tv_temp.setText(String.valueOf(bean.getTemp()+"°C"));
        //holder.img.setImageResource(bean.getImage());
        new DownloadImageTask(holder.img)
                .execute("http://10.6.1.89/img/"+bean.getImage()+".png");


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

}
