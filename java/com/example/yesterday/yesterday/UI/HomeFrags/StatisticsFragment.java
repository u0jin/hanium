package com.example.yesterday.yesterday.UI.HomeFrags;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.yesterday.yesterday.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

//통계 화면 Fragment
public class StatisticsFragment extends Fragment {

    private ViewGroup rootView;

    Button startBtn;
    Button endBtn;
    TextView startDateView;
    TextView endDateView;

    // 각각 시작 종료  두가지의 날짜(년,월,일)값을 저장 해두고, 통계를 db값에서 불러온다.
    private int startYear;
    private int startMonth;
    private int startDay;
    private int endYear;
    private int endMonth;
    private int endDay;

    public StatisticsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    //생성자와 onCreateView만 있어도 ok
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView=(ViewGroup)inflater.inflate(R.layout.fragment_statistics,container,false);
        // Inflate the layout for this fragment

        //막대바 차트 생성
        HorizontalBarChart horizontalBarChart = (HorizontalBarChart)rootView.findViewById(R.id.chart);

        //시작 종료 날짜를 설정 할 수 있는 버튼
        startBtn = (Button) rootView.findViewById(R.id.button);
        endBtn = (Button) rootView.findViewById(R.id.button2);

        //시작 종료 날짜 보여주는 텍스트
        startDateView = (TextView)rootView.findViewById(R.id.textView);
        endDateView = (TextView)rootView.findViewById(R.id.textView2);


        // 각각 시작 종료  두가지의 날짜(년,월,일)값을 저장 해두고, 통계를 db값에서 불러온다.
        final Calendar c = Calendar.getInstance();
        startYear = c.get(Calendar.YEAR);
        startMonth = c.get(Calendar.MONTH) -1;
        startDay = c.get(Calendar.DATE);
        endYear = c.get(Calendar.YEAR);
        endMonth = c.get(Calendar.MONTH);
        endDay = c.get(Calendar.DATE);

        //시작날짜를 입력 하기위한 버튼 이벤트
        final DatePickerDialog startDatePickerDialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                startYear = year;
                startMonth = month;
                startDay = dayOfMonth;
                updateDate(startDateView,startYear,startMonth,startDay);
            }
        }, startYear, startMonth, startDay);

        //종료날짜를 입력 하기위한 버튼 이벤트
        final DatePickerDialog endDatePickerDialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                endYear = year;
                endMonth = month;
                endDay = dayOfMonth;
                updateDate(endDateView,endYear,endMonth,endDay);
            }
        }, endYear, endMonth , endDay);

        //버튼을 누르면 날짜 설정 다이어로그를 띄어준다.
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDatePickerDialog.show();
            }
        });
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endDatePickerDialog.show();
            }
        });

        //통계 플래그 먼트 생성시 초기값 입력 : 시작텍스트에는 한달 전 날짜 값, 종료 텍스트에는 오늘 날짜 값 디폴트 지정
        updateDate(startDateView,startYear,startMonth,startDay);
        updateDate(endDateView,endYear,endMonth,endDay);


        //차트 그리기
        getFoodValue(horizontalBarChart);


        return rootView;
    }

    //시작, 종료 날짜 텍스트 값 update
    private void updateDate(TextView view,int Year,int Month,int Day){
        String str = Year +"년"+(Month +1)+"월"+ Day +"일";
        view.setText(str);
    }

    //시작 , 종료 날짜를 사용해 db 값을 불러와 막대 차트에 값 입력 해주는 메소드
    private void getFoodValue(HorizontalBarChart horizontalBarChart){
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(5f, 16));
        entries.add(new BarEntry(4f, 10));
        entries.add(new BarEntry(3f, 7));
        entries.add(new BarEntry(2f, 3));
        entries.add(new BarEntry(1f, 2));
        entries.add(new BarEntry(0f, 1));

        BarDataSet dataset = new BarDataSet(entries, "# of Colls");
        dataset.setDrawValues(true);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("커피");
        labels.add("김치찌개");
        labels.add("쌀국수");
        labels.add("초밥");
        labels.add("파스타");
        labels.add("라면");

        BarData data = new BarData(dataset);

        // Hide grid lines
        //horizontalBarChart.getAxisLeft().setEnabled(false);
        //horizontalBarChart.getAxisRight().setEnabled(false);
        // Hide graph description
        horizontalBarChart.getDescription().setEnabled(false);
        // Hide graph legend
        horizontalBarChart.getLegend().setEnabled(false);

        // Design
        dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.DKGRAY);

        horizontalBarChart.setData(data);

        horizontalBarChart.getXAxis().setDrawGridLines(false);
        horizontalBarChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        horizontalBarChart.getXAxis().setDrawLabels(true);
        horizontalBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        horizontalBarChart.getXAxis().setXOffset(-10);
        horizontalBarChart.setExtraOffsets(50,0,0,0);

        horizontalBarChart.getXAxis().setAxisLineWidth(1);

        horizontalBarChart.getXAxis().setDrawAxisLine(false);
        horizontalBarChart.getAxisRight().setDrawLabels(false);
        //horizontalBarChart.getAxisRight().setSpaceBottom(50);
        //horizontalBarChart.getXAxis().setAxisMaximum(10);

        //horizontalBarChart.getAxisLeft().setDrawGridLines(false);
        horizontalBarChart.animateY(5000);
    }

    //chart 디자인 설정
    private void DrawChart(HorizontalBarChart horizontalBarChart) {
    }

}
