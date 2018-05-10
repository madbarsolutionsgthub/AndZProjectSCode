package Adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.csi.los.R;

import java.util.List;

import Model.ListItem;

/**
 * Created by Rayhan on 12/24/2017.
 */

public class AdapterForLoanStatus extends RecyclerView.Adapter<AdapterForLoanStatus.ViewHolder> {
    private List<ListItem>listItems;
    private Context context;
    String pending = "pending";
    String getStatus;
    public AdapterForLoanStatus(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.existing_loan_card
                ,parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem=listItems.get(position);
        holder.caseNumber.setText(listItem.getCaseNo());
        holder.name.setText(listItem.getName());
        holder.loanType.setText(listItem.getLoanType());
        holder.status.setText(listItem.getStatus());
        holder.loanAmount.setText(listItem.getLoanAmount()+" TK");
        getStatus = holder.status.getText().toString();
        if (getStatus.equals(pending)){
            holder.status.setTextColor(Color.parseColor("#e53935"));
            holder.loanAmount.setTextColor(Color.parseColor("#e53935"));
        }
    }
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView caseNumber;
        public TextView name;
        public TextView status;
        public TextView loanAmount;
        public TextView loanType;
        public ViewHolder(View itemView) {
            super(itemView);
            caseNumber=(TextView) itemView.findViewById(R.id.textViewCaseNo);
            name= (TextView) itemView.findViewById(R.id.textViewName);
            status= (TextView) itemView.findViewById(R.id.textViewstatus);
            loanAmount= (TextView) itemView.findViewById(R.id.textViewloanAmount);
            loanType= (TextView) itemView.findViewById(R.id.textViewLoantype);
        }
    }
}
