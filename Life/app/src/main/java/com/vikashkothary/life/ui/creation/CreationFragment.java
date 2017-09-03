package com.vikashkothary.life.ui.creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.vikashkothary.life.R;
import com.vikashkothary.life.ui.base.BaseActivity;
import com.vikashkothary.life.ui.base.BaseFragment;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class CreationFragment extends BaseFragment implements CalendarDatePickerDialogFragment.OnDateSetListener, View.OnClickListener, RadialTimePickerDialogFragment.OnTimeSetListener {

    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private static final String FRAG_TAG_TIME_PICKER = "timePickerDialogFragment";

    @BindView(R.id.button_date)
    Button mDateButton;
    @BindView(R.id.button_time)
    Button mTimeButton;

    public static void attachFragment(BaseActivity activity) {
        CreationFragment fragment = new CreationFragment();
        activity.setFragment(fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseActivity) getActivity()).activityComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_creation, container, false);
        ButterKnife.bind(this, fragmentView);

        getActivity().setTitle("New");

        mDateButton.setOnClickListener(this);
        mTimeButton.setOnClickListener(this);

        return fragmentView;
    }

    public void onClick(View v) {
        Calendar today = Calendar.getInstance();
        switch (v.getId()) {
            case R.id.button_date:
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(CreationFragment.this)
                        .setFirstDayOfWeek(Calendar.SUNDAY)
                        .setPreselectedDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH))
                        .setDoneText("Yay")
                        .setCancelText("Nope")
                        .setThemeDark();
                cdp.show(getFragmentManager(), FRAG_TAG_DATE_PICKER);
                break;
            case R.id.button_time:
                RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                        .setOnTimeSetListener(CreationFragment.this)
                        .setStartTime(today.get(Calendar.HOUR), today.get(Calendar.MINUTE))
                        .setDoneText("Yay")
                        .setCancelText("Nope")
                        .setThemeDark();
                rtpd.show(getFragmentManager(), FRAG_TAG_TIME_PICKER);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        CalendarDatePickerDialogFragment cdp = (CalendarDatePickerDialogFragment) getFragmentManager()
                .findFragmentByTag(FRAG_TAG_DATE_PICKER);
        RadialTimePickerDialogFragment rtpd = (RadialTimePickerDialogFragment) getFragmentManager().findFragmentByTag(FRAG_TAG_TIME_PICKER);
        if (rtpd != null) {
            rtpd.setOnTimeSetListener(this);
        }
        if (cdp != null) {
            cdp.setOnDateSetListener(this);
        }
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        mDateButton.setText(getString(R.string.calendar_date_picker_result_values, year, monthOfYear, dayOfMonth));
    }

    @Override
    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
        mTimeButton.setText(getString(R.string.radial_time_picker_result_value, hourOfDay, minute));
    }
}
