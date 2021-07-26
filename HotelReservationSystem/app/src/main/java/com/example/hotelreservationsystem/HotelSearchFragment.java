package com.example.hotelreservationsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HotelSearchFragment extends Fragment {

    // Need to declare the instance of the views so that the values of each field
    //can be captured through the xml element id, then retrieve information using view
    View view;
    TextView titleTextView, searchTextConfirmationTextView;
    EditText guestcountEditText;
    Button confirmSearchButton, searchButton;
    DatePicker checkInDatePickerView, checkOutDatePickerView;
    @Nullable
    //@org.jetbrains.annotations.Nullable
    @Override
    //public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_search_layout,container,false);
        return view;
    }

    @Override
    //public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

        titleTextView = view.findViewById(R.id.title_text_view);
        guestcountEditText = view.findViewById(R.id.guests_count_edit_view);
        confirmSearchButton = view.findViewById(R.id.confirm_my_search_button);
        searchTextConfirmationTextView = view.findViewById(R.id.search_confirm_text_view);
        checkInDatePickerView = view.findViewById(R.id.checkin_date_picker_view);
        checkOutDatePickerView = view.findViewById(R.id.checkout_date_picker_view);
        searchButton=view.findViewById(R.id.search_button);
        //set text
        titleTextView.setText(R.string.welcome_text);
        //set button
        //define click listener
        confirmSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkInDate = getdateFromCalendar(checkInDatePickerView);
                String checkOutDate = getdateFromCalendar(checkOutDatePickerView);
                String numberofguests = guestcountEditText.getText().toString();
                searchTextConfirmationTextView.setText("The checkin is "+ checkInDate+" and checkout is: "+checkOutDate+" and the number of guests are "+numberofguests);
            }
        });
        //Onclick Listener for search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkInDate = getdateFromCalendar(checkInDatePickerView);
                String checkOutDate = getdateFromCalendar(checkOutDatePickerView);
                String numberOfGuests=guestcountEditText.getText().toString();
                // Inside the bundle we put the keys for the check in date, check out date and no. of guests
                // The key names need to be exact otherwise retrieval of values is impossible.
                Bundle bundle = new Bundle();
                bundle.putString("check in date", checkInDate);
                bundle.putString("check out date", checkOutDate);
                bundle.putString("number of guests", numberOfGuests);

                HotelListFragment hotelListFragment= new HotelListFragment();
               // hotelListFragment.setArguments(bundle);
                hotelListFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                // Now, the next screen will appear along with the existing one
                fragmentTransaction.replace(R.id.main_layout,hotelListFragment);
                //By removing the existing one, it takes us tot the next page
                fragmentTransaction.remove(HotelSearchFragment.this);
                //To save the memory from keeping track of the previous screen
                // we are putting it to null value
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();



            }
        });
    }

    // Formatting the date through an external function
    private String getdateFromCalendar(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = simpleDateFormat.format(calendar.getTime());

        return formattedDate;
    }
    /*private String getdateFromCalendarCheckOut(){
        int day = checkOutDatePickerView.getDayOfMonth();
        int month = checkOutDatePickerView.getMonth();
        int year = checkOutDatePickerView.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = simpleDateFormat.format(calendar.getTime());

        return formattedDate;
    }*/
}
