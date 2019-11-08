package com.rishabh.bloodbank.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.rishabh.bloodbank.Adapters.RequestAdapter.ViewHolder;
import com.rishabh.bloodbank.DataModels.RequestDataModel;
import com.rishabh.bloodbank.R;
import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<ViewHolder> {

  private List<RequestDataModel> dataSet;
  private Context context;

  public RequestAdapter(
      List<RequestDataModel> dataSet, Context context) {
    this.dataSet = dataSet;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.request_item_layout, parent, false);
    return new ViewHolder(view);
  }


  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder,
      final int position) {
    holder.message.setText(dataSet.get(position).getMessage());
    Glide.with(context).load(dataSet.get(position).getImageUrl()).into(holder.imageView);
    holder.callButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        // for later
      }
    });

    holder.shareButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        //implement later
      }
    });
  }


  @Override
  public int getItemCount() {
    return dataSet.size();
  }


  class ViewHolder extends RecyclerView.ViewHolder {

    TextView message;
    ImageView imageView, callButton, shareButton;

    ViewHolder(final View itemView) {
      super(itemView);
      message = itemView.findViewById(R.id.message);
      imageView = itemView.findViewById(R.id.image);
      callButton = itemView.findViewById(R.id.call_button);
      shareButton = itemView.findViewById(R.id.share_button);
    }

  }

}
