package com.demoapp.expedia;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class ListFragment extends Fragment {

    private ArrayList<Hotel> hotelList;
    private DataAdapter dataAdapter;
    private RecyclerView recyclerView;
    private Spinner spinner;
    private static final String sortParams[] = {"Guest Rating", "Price", "Hotel Name"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(@NonNull View view) {
        Button btnViewMap = view.findViewById(R.id.view_map);
        recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        spinner = view.findViewById(R.id.spinner);

        loadJSONFromAsset();
        populateSortParameters();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    sortByGuestRating();
                } else if(position == 1) {
                    sortByPrice();
                } else {
                    sortByName();
                }
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnViewMap.setOnClickListener((View v) -> {
            Fragment fragment = new MapFragment();
            FragmentManager fragmentManager = getFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("list",hotelList);
            fragment.setArguments(bundle); //hotel list data sent to SecondFragment
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });

    }

    /**
     * Perform Sorting of Hotel List based on Hotel Name.
     */
    private void sortByName() {
        Collections.sort(hotelList, (l1, l2) -> l1.getHotelName().compareTo(l2.getHotelName()));
    }

    /**
     * Perform Sorting of Hotel List based on Guest Rating.
     */
    private void sortByGuestRating() {
        Collections.sort(hotelList, (l1, l2) -> {
            if (l1.getGuestRating() > l2.getGuestRating()) {
                return 1;
            } else if (l1.getGuestRating() < l2.getGuestRating()) {
                return -1;
            } else {
                return 0;
            }
        });
    }

    /**
     * Perform sorting of hotel list based on price.
     */
    private void sortByPrice() {
        Collections.sort(hotelList, new PriceComparator());
    }

    /**
     * Populate the data needed for sorting the results.
     */
    private void populateSortParameters() {
        ArrayAdapter<String> adapterSortParam = new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, sortParams);

        adapterSortParam.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapterSortParam);
    }

    /** This method helps in parsing the static json file from assets
     *  In case of real world scenario, we can get the json response for
     *  http request inside an async task or use RxJava.
     *
     * */
    public void loadJSONFromAsset() {
        hotelList = new ArrayList<>();
        String json = null;
        try {
            InputStream inputStream = getActivity().getAssets().open(getString(R.string.json_file_name));
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, getString(R.string.utf_8));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray(getString(R.string.hotels));

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Hotel hotel = new Hotel();
                hotel.setStarRating(jsonObject.getLong(getString(R.string.star_rating)));
                hotel.setLatitude(jsonObject.getDouble(getString(R.string.latitude)));
                hotel.setLoyaltyPointsEarned(jsonObject.getInt(getString(R.string.loyalty_points_earned)));
                hotel.setDescription(jsonObject.getString(getString(R.string.description)));
                hotel.setHotelImageURL(jsonObject.getString(getString(R.string.image_url)));
                hotel.setGuestRating(jsonObject.getLong(getString(R.string.guest_rating)));
                hotel.setLongitude(jsonObject.getDouble(getString(R.string.longitude)));
                hotel.setHotelName(jsonObject.getString(getString(R.string.hotel_name)));
                hotel.setPrice(jsonObject.getString(getString(R.string.price)));
                hotel.setDiscountMessage(jsonObject.getString(getString(R.string.discount_message)));

                hotelList.add(hotel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        dataAdapter = new DataAdapter(hotelList, getActivity().getApplicationContext());
        recyclerView.setAdapter(dataAdapter);
    }
}
