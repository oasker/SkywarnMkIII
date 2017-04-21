package com.example.oliverasker.skywarnmarkii.Fragments.QueryReportsAttributesFragments;

/**
 * Created by oliverasker on 4/16/17.
 */

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.oliverasker.skywarnmarkii.R;
import com.example.oliverasker.skywarnmarkii.Utility.DateUtility;

import java.util.Calendar;

//import android.icu.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QueryReportAttributesSingleDayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QueryReportAttributesSingleDayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryReportAttributesDateRangeFragment extends Fragment {
    private static final String TAG = "QueryReprtAttrSingleDay";
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
    private Button setStartDayButton;
    private Button setEndDayButton;

    private TextView dateRangeToQueryTV;
    private StringBuilder dateRangeStringBuilder;

    private StringBuilder startDateString = new StringBuilder();
    private long startDateEpoch;

    private StringBuilder endDateString = new StringBuilder();
    private long endDateEpoch;

    private int startDay;
    private int startMonth;
    private int startYear;

    private int endDay;
    private int endMonth;
    private int endYear;
    private long dateOfEvent;

    private int mMonth;
    private int mDay;
    private int mYear;

    private Calendar startDate;
    private Calendar endDate;


    private TextView dateTV;

    private OnFragmentInteractionListener mListener;

    public QueryReportAttributesDateRangeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QueryReportAttributesSingleDayFragment.
     */
    public static QueryReportAttributesSingleDayFragment newInstance(String param1, String param2) {
        QueryReportAttributesSingleDayFragment fragment = new QueryReportAttributesSingleDayFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        startDateString.append(DateUtility.getYerdaysDateString());
        endDateString.append(DateUtility.getTodaysDateString());
        startDateEpoch = DateUtility.getYesterdaysEpoch();
        endDateEpoch = DateUtility.getTodaysEpoch();

        dateRangeStringBuilder = new StringBuilder();
        dateRangeStringBuilder.append(startDateString + " to " + endDateString);

        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_query_report_attributes_date_range_select, container, false);

        //  initialize widgets
        dateRangeToQueryTV = (TextView) v.findViewById(R.id.date_range_to_query_TV);
        dateRangeToQueryTV.setText(dateRangeStringBuilder.toString());


        setStartDayButton = (Button) v.findViewById(R.id.set_start_date_button);
        setStartDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                }
//                Set datepicker start date
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    Log.d(TAG, dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                    startDay = dayOfMonth;
                                    startMonth = monthOfYear;
                                    startYear = year;
                                    Calendar c = null;
                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                        c = Calendar.getInstance();
                                        c.set(year, monthOfYear, dayOfMonth);
                                        startDateEpoch = c.getTimeInMillis();
                                        startDate = c;
                                    }
                                    startDateString.setLength(0);
                                    startDateString.append((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                                    dateRangeToQueryTV.setText(startDateString + " to " + endDateString);
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMaxDate(endDateEpoch);
                    datePickerDialog.show();
                }
            }
        });

        setEndDayButton = (Button) v.findViewById(R.id.set_end_date_button);
        setEndDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                }
//                Set datepicker start date
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    //Log.d(TAG, dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                    Calendar c = null;
                                    endDay = dayOfMonth;
                                    endMonth = monthOfYear;
                                    endYear = year;
                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                        c = Calendar.getInstance();
                                        c.set(year, monthOfYear, dayOfMonth);
                                        endDateEpoch = c.getTimeInMillis();
                                        endDate = c;
                                    }
                                    // dateTV.setText((monthOfYear + 1)+ "/" + (dayOfMonth + "/"  + year));
                                    endDateString.setLength(0);
                                    endDateString.append((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                                    dateRangeToQueryTV.setText(startDateString + " to " + endDateString);
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(startDateEpoch);
                    datePickerDialog.getDatePicker().setMaxDate(DateUtility.getTodaysEpoch());
                    datePickerDialog.show();
                }
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndDay() {
        return endDay;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
