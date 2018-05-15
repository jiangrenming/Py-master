package com.yuqi.admin.py.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.yuqi.admin.py.*;
import com.yuqi.admin.py.bean.RewardBean;

/**
 * Created by Administrator on 2018/3/21/021.
 */

public class RewardAdapter extends BaseAdapter implements ListAdapter {

    private RewardBean rewardBean;
    private Context context;

    public RewardAdapter(Context context, RewardBean rewardBean) {
        super();
        this.rewardBean = rewardBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return rewardBean.getData().getMyRewards().size();
    }

    @Override
    public Object getItem(int position) {
        return rewardBean.getData().getMyRewards().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold hold = null;
        if (convertView == null) {
            hold = new ViewHold();
            convertView = View.inflate(context,R.layout.item_reward, null);
            hold.reward_registertime = (TextView) convertView.findViewById(R.id.reward_registertime);
            hold.reward_accounts = (TextView) convertView.findViewById(R.id.reward_accounts);
            hold.reward_getMoney = (TextView) convertView.findViewById(R.id.reward_getMoney);
            hold.reward_item = (LinearLayout)convertView.findViewById(R.id.reward_item);
            convertView.setTag(hold);

            hold.reward_registertime.setText(rewardBean.getData().getMyRewards().get(position).getRegisterTime());
            hold.reward_accounts.setText(rewardBean.getData().getMyRewards().get(position).getAccounts());
            hold.reward_getMoney.setText(String.valueOf(rewardBean.getData().getMyRewards().get(position).getGetMoney())+"元");
        }else {
            hold = (ViewHold) convertView.getTag();
        }
        return convertView;
    }

    class ViewHold {
        TextView reward_registertime,reward_accounts,reward_getMoney;
        LinearLayout reward_item;
    }
}
