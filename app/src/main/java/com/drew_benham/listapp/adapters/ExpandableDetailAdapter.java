package com.drew_benham.listapp.adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.drew_benham.listapp.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableDetailAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> mediaHeaderList;
    private HashMap<String, List<Pair<String, String>>> listHashMap;

    public ExpandableDetailAdapter(Context context, List<String> mediaHeaderList, HashMap<String, List<Pair<String, String>>> listHashMap) {
        this.context = context;
        this.mediaHeaderList = mediaHeaderList;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return mediaHeaderList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(mediaHeaderList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mediaHeaderList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(mediaHeaderList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String header = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_group, null);
        }

        TextView headerTextView = convertView.findViewById(R.id.lblListHeader);
        headerTextView.setText(header);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Pair<String, String> listItem = (Pair<String, String>) getChild(groupPosition, childPosition);
        String left = listItem.first;
        String right = listItem.second;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_item, null);
        }

        TextView leftText = convertView.findViewById(R.id.leftDetailItem);
        TextView rightText = convertView.findViewById(R.id.rightDetailItem);

        leftText.setText(left);
        rightText.setText(right);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
