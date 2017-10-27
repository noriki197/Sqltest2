package englishvocabulary.gameloft.com.sqltest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by PC on 26-Oct-17.
 */

public class studentAdapter extends BaseAdapter{
    Context context;
    int layout;
    List<CongViec> congViecList;

    public studentAdapter(Context context, int layout, List<CongViec> congViecList) {
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
