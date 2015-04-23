package br.ufpe.cin.if1001.chatoffline.gui.contact;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.ufpe.cin.if1001.chatoffline.R;

public class ContactAdapter extends BaseAdapter {

    private Activity mContext;
    private List<Contact> mContactList;
    private LayoutInflater mLayoutInflater = null;

    public ContactAdapter (Activity context, List<Contact> contactList) {
        mContext = context;
        mContactList = contactList;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mContactList.size();
    }

    @Override
    public Object getItem(int position) {
        return mContactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ContactViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.row_contact, null);
            viewHolder = new ContactViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ContactViewHolder) view.getTag();
        }

        viewHolder.mTxtName.setText(mContactList.get(position).getName());
        viewHolder.mTxtMessage.setText(mContactList.get(position).getMessage());
        
        if(position % 2 == 0) {
            viewHolder.mRowContact.setBackgroundResource(R.color.blue_light_3);
        } else {
            viewHolder.mRowContact.setBackgroundResource(R.color.blue_light_4);
        }
        
        if(mContactList.get(position).getStatus().equals(Contact.Status.STATUS_ONLINE)) {
        	viewHolder.mImgStatus.setBackgroundResource(R.drawable.status_green);
        } else {
        	viewHolder.mImgStatus.setBackgroundResource(R.drawable.status_gray);
        }

        return view;
    }

    class ContactViewHolder {

        public TextView mTxtName;
        public TextView mTxtMessage;
        public LinearLayout mRowContact;
        public ImageView mImgStatus;
        
        public ContactViewHolder(View base) {
            mTxtName = (TextView) base.findViewById(R.id.txtName);
            mTxtMessage = (TextView) base.findViewById(R.id.txtMessage);
            mRowContact = (LinearLayout) base.findViewById(R.id.rowContact);
            mImgStatus = (ImageView) base.findViewById(R.id.imgStatus);
        }
    }

}
