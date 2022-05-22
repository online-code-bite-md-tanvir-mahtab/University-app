package com.tanvircodder.exmple.uvinvercitys;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tanvircodder.exmple.uvinvercitys.model.Util;

import java.util.List;

public class VersityAdapter extends RecyclerView.Adapter<VersityAdapter.VersityHolder>{
    private Context context;
    private List<Util> mData;
    public VersityAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public VersityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.versity_list_item,parent,false);
        return new VersityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersityHolder holder, int position) {
        Util util = mData.get(position);
        holder.mTextView.setText(util.getName());
        holder.mDomainView.setText(util.getmDomains());
    }

    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        return mData.size();
    }
    public void swapData(List<Util> mData){
        this.mData = mData;
        if (mData != null){
            notifyDataSetChanged();
        }
    }

    class VersityHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextView;
        private TextView mDomainView;
        public VersityHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.name_of_the_versity);
            mDomainView = (TextView) itemView.findViewById(R.id.domain_of_the_versity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Util util = mData.get(getAdapterPosition());
            Toast.makeText(context,"Going to the web page",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context,WebVersityView.class);
            intent.putExtra("web_url",util.getmUrl());
            intent.putExtra("name",util.getName());
            context.startActivity(intent);

        }
    }
}
