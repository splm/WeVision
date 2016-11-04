package me.splm.wevisiondemo;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

public class SampleAdapter extends AbsAdapter<SampleAnimModel>{

	public SampleAdapter(Context context, List<SampleAnimModel> mDatas, int itemLayoutId) {
		super(context, mDatas, itemLayoutId);
	}

	@Override
	protected void convert(ViewHolder viewHolder, SampleAnimModel item, int position) {
		TextView tv=viewHolder.getView(R.id.animation_item_name_tv);
		String name=item.getName();
		tv.setText(name);
	}

}
