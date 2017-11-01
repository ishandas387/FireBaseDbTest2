package com.ishan387.firebasedbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ishan387.model.Car;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button addCar;
    Spinner spin;
    ListView carList;

    DatabaseReference cars;
    List<Car> listOfCars;

    @Override
    protected void onStart() {
        super.onStart();
        cars.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listOfCars.clear();
                for (DataSnapshot d : dataSnapshot.getChildren())
                {
                    Car c = d.getValue(Car.class);
                    listOfCars.add(c);
                }
                CarListAdapter adapter = new CarListAdapter(MainActivity.this,listOfCars);
                carList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cars = FirebaseDatabase.getInstance().getReference("Cars");

        name = (EditText) findViewById(R.id.carname);
        spin = (Spinner) findViewById(R.id.spinner);
        addCar = (Button) findViewById(R.id.addcarbutton);
        carList = (ListView) findViewById(R.id.listviewcars) ;
        listOfCars = new ArrayList<>();

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCarToDb();
            }
        });
    }

    private void addCarToDb() {

        String car = name.getText().toString().trim();
        String type = spin.getSelectedItem().toString();

        if(!car.isEmpty())
        {
            String id = cars.push().getKey();

            Car c = new Car(id,car,type);
            cars.child(id).setValue(c);
            Toast.makeText(this,"Added car", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Please enter a car name", Toast.LENGTH_LONG).show();
        }

    }
}
