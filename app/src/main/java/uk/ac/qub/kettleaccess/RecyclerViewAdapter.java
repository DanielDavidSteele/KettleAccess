package uk.ac.qub.kettleaccess;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Bean> mData;
    Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<Bean> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
                dialog_bean_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getImage());
                dialog_desc_tv.setText(mData.get(vHolder.getAdapterPosition()).getDescription());
                myDialog.show();
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.tv_bean.setText(mData.get(position).getName());
        holder.tv_origin.setText(mData.get(position).getOrigin());
        holder.tv_temp.setText(String.valueOf(mData.get(position).getTemp()+"°C"));
        holder.img.setImageResource(mData.get(position).getImage());

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
}
