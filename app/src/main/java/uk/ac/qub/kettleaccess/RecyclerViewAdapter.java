package uk.ac.qub.kettleaccess;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Bean> mData;

    public RecyclerViewAdapter(Context mContext, List<Bean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_bean, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.tv_bean.setText(mData.get(position).getName());
        holder.tv_origin.setText(mData.get(position).getOrigin());
        holder.tv_temp.setText(String.valueOf(mData.get(position).getTemp()+"°C"));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_bean;
        private TextView tv_origin;
        private TextView tv_temp;

        public MyViewHolder(View itemView){
            super(itemView);

            tv_bean = (TextView) itemView.findViewById(R.id.name_bean);
            tv_origin = (TextView) itemView.findViewById(R.id.origin_bean);
            tv_temp = (TextView) itemView.findViewById(R.id.temp_bean);

        }
    }
}
