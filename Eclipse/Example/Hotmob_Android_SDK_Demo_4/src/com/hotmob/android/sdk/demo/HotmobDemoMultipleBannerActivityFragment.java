package com.hotmob.android.sdk.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hotmob.sdk.manager.HotmobManager;
import com.hotmob.sdk.manager.HotmobManagerListener;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class HotmobDemoMultipleBannerActivityFragment extends Fragment{
	protected final String TAG = this.getClass().getName();

    private ListView mListView;

    private ArrayList<View> mList;
    private CellArrayAdapter mCellArrayAdapter;

    private final int mBannerPosition = 3;
    private final int mCellNum = 50;
    
    private View mBannerView;
    
    public HotmobDemoMultipleBannerActivityFragment(){
    	
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	
    	View view = inflater.inflate(R.layout.fragment_hotmob_demo_multiple_banner, container, false);

        mListView = (ListView)view.findViewById(R.id.hotmob_demo_multiple_banner_list);

        this.createList();
    	return view;
    }
    
    
    private void createList() {
        String [] adCodes = new String[] {
                "hotmob_uat_android_image_inapp_banner",
                "hotmob_uat_android_image_external_banner",
                "hotmob_uat_android_image_sms_banner",
                "hotmob_uat_android_image_internal_banner"
        };

        if (mList == null) {
        	mList = new ArrayList<View>();
        }

        int totalCount = 20;
        for (int i = 0 ; i < totalCount; i++) {
            if (i%5 == 2) {
                createBannerItem(adCodes[i / 5]);
            } else {
                createNormalItem("This is a item (#" + i + ")");
            }
        }

        CellArrayAdapter cellArrayAdapter = new CellArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(cellArrayAdapter);
    }
	
    private void createNormalItem(String title) {
        LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cellView = inflater.inflate(R.layout.listview_cell_item, null);
        TextView cellTextView = (TextView)cellView.findViewById(R.id.listview_cell_item_textview);
        cellTextView.setText(title);
        mList.add(cellView);
    }

    private void createBannerItem(String adCode) {
        LayoutInflater bannerInflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View bannerView = bannerInflater.inflate(R.layout.listview_banner_cell_item, null);
        final LinearLayout bannerLayout = (LinearLayout)bannerView.findViewById(R.id.list_banner_cell_layout);

        HotmobManagerListener listener = new HotmobManagerListener() {
            public void didLoadBanner(View bannerView) {
                bannerLayout.addView(bannerView);
            }

            public void openInternalCallback(View bannerView, String url) {
                super.openInternalCallback(bannerView, url);
            }

            public void onResizeBanner(View bannerView) {
                super.onResizeBanner(bannerView);
            }
        };

        HotmobManager.getBanner(getActivity(), listener, HotmobManager.getScreenWidth(getActivity()), adCode, adCode);
        mList.add(bannerView);
    }
	
	@Override
    public void onDestroy() {
        super.onDestroy();
        HotmobManager.destroyBanner(mBannerView);
    }
    
    private class CellArrayAdapter extends ArrayAdapter<View> {
        private HashMap<Integer, View> mIdMap;

        public CellArrayAdapter(Context context, int textViewResourceId, List<View> objects) {
            super(context, textViewResourceId, objects);

            mIdMap = new HashMap<Integer, View>();

            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(i, objects.get(i));
            }
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = mIdMap.get(position);
            return rowView;
        }
    }
}
