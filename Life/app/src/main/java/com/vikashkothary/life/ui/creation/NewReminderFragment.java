package com.vikashkothary.life.ui.creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.vikashkothary.life.R;
import com.vikashkothary.life.data.model.Reminder;
import com.vikashkothary.life.ui.base.BaseActivity;
import com.vikashkothary.life.ui.base.BaseFragment;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class NewReminderFragment extends BaseFragment implements CalendarDatePickerDialogFragment.OnDateSetListener, View.OnClickListener, RadialTimePickerDialogFragment.OnTimeSetListener {

    private static final String FRAG_TAG_DATE_PICKER = "date_picker_dialog_fragment";
    private static final String FRAG_TAG_TIME_PICKER = "time_picker_dialog_fragment";

    private Calendar mCalendar = Calendar.getInstance();

    @BindView(R.id.edit_text_message)
    EditText mMessageEditText;
    @BindView(R.id.button_date)
    Button mDateButton;
    @BindView(R.id.button_time)
    Button mTimeButton;

    public static void attachFragment(BaseActivity activity) {
        NewReminderFragment fragment = new NewReminderFragment();
        activity.setFragment(fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_new_reminder, container, false);
        ButterKnife.bind(this, fragmentView);

        getActivity().setTitle(R.string.title_fragment_new_reminder);

        return fragmentView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.options_creation, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                Reminder r = Reminder.builder()
                        .setMessage(mMessageEditText.getText().toString())
                        .setDatetime(mCalendar.getTime())
                        .build();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick({R.id.button_date, R.id.button_time})
    public void onClick(View v) {
        Calendar today = Calendar.getInstance();
        switch (v.getId()) {
            case R.id.button_date:
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(NewReminderFragment.this)
                        .setFirstDayOfWeek(Calendar.SUNDAY)
                        .setPreselectedDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH))
                        .setDoneText("Yay")
                        .setCancelText("Nope")
                        .setThemeDark();
                cdp.show(getFragmentManager(), FRAG_TAG_DATE_PICKER);
                break;
            case R.id.button_time:
                RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                        .setOnTimeSetListener(NewReminderFragment.this)
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
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, monthOfYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

    }

    @Override
    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
        mTimeButton.setText(getString(R.string.radial_time_picker_result_value, hourOfDay, minute));
        mCalendar.set(Calendar.HOUR, hourOfDay);
        mCalendar.set(Calendar.MINUTE, minute);
    }
}
