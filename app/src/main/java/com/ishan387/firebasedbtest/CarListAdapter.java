package com.ishan387.firebasedbtest;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ishan387.model.Car;

import java.util.List;

/**
 * Created by ishan on 01-11-2017.
 */

public class CarListAdapter extends ArrayAdapter<Car> {

    private Activity context;
    private List<Car> carsList;

    public CarListAdapter(Activity context, List<Car> list)
    {
        super(context, R.layout.listlayout);
        this.carsList= list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItems = inflater.inflate(R.layout.listlayout,null,true);
        TextView texviewName = (TextView) listViewItems.findViewById(R.id.textView);
        TextView texviewType = (TextView)listViewItems.findViewById(R.id.type);
        Car c = carsList.get(position);
        texviewName.setText(c.getName());
        texviewType.setText(c.getType());
        return listViewItems;

    }
}
