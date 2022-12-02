//by수경 showKPIChart.html 실행하면 곧바로 실행될 함수 지정
drawChartAjax();
drawProbationChartAjax();
//by수경 학적 신청과 승인 KPI 차트 그리기 위하여 데이터를 가져올 ajax 실행 함수
function drawChartAjax(){
	$.ajax({
		url: '/emp/showKPIAjax', //요청경로
		type: 'post',
		data: {}, //필요한 데이터
		success: function(KPIchartDataMap) {
			drawChart(KPIchartDataMap);
		},
		error: function() {
			alert('실패');
		}
	})
};

//by수경 학적 신청과 승인 KPI 차트 그리는 함수
function drawChart(KPIchartDataMap){
	var options = {
		series: [{
			name: '신청 내역',
			type: 'column',
			data: KPIchartDataMap.applyNoList1
		}, {
			name: '승인 내역',
			type: 'line',
			data: KPIchartDataMap.approvalDateNoList1,
		}],
		chart: {
			height: 420,
			type: 'line',
		},
		stroke: {
			width: [0, 4]
		},
		title: {
			text: '2022년 학적신청내역 대비 승인내역 KPI',
			align: 'left',
			   offsetX: 50,
          	
			style: {
	            fontSize: '20px',
          	}
		},
		 colors: ['#33b2df', '#546E7A'
          
        ],
		dataLabels: {
			enabled: true,
			enabledOnSeries: [1]
		},
		labels: KPIchartDataMap.approvalDateList,
		xaxis: {
			type: 'String',
		},
		yaxis: [{
			/*title: {
				text: '신청 내역',
				style: {
	            	fontSize: '18px',
          		}
			},*/

		}, {
			opposite: true,
			/*title: {
				text: '승인 내역',
				style: {
	            	fontSize: '18px',
          		}
			}*/
		}]
	};

	var chart = new ApexCharts(document.querySelector("#approvalKPI"), options);
	chart.render();
};

//by수경 학사경고와 제적 차트 그리기 위한 데이터 가져오는 ajax 함수
function drawProbationChartAjax(){
	
	$.ajax({
		url: '/emp/showProbationStuOutChartAjax', //요청경로
		type: 'post',
		data: {}, //필요한 데이터
		success: function(probationStuOutChartMap) {
			console.log(probationStuOutChartMap);
			drawProbationChart(probationStuOutChartMap);
		},
		error: function() {
			alert('실패');
		}
	})	
};

function drawProbationChart(probationStuOutChartMap){
	 var options = {
          series: [{
            name: '학사경고',
          data: probationStuOutChartMap.probNoList
        }, {
          name: '제적',
          data: probationStuOutChartMap.stuOutNoList
        }],
          chart: {
          type: 'bar',
          height: 420
        },
        plotOptions: {
          bar: {
            horizontal: false,
            dataLabels: {
              position: 'top',
            },
          }
        },
        dataLabels: {
          enabled: true,
          offsetX: -6,
          style: {
            fontSize: '12px',
            colors: ['#fff']
          }
        },
        stroke: {
          show: true,
          width: 1,
          colors: ['#fff']
        },
   		  title: {
        text: '2022년 2학기 학사경고 및 제적 건수',
        align: 'left',
		offsetX: 50,
        style: {
	         fontSize: '20px',
        }
        },
         colors: ['#90ee7e','#69d2e7'
        ], 
        tooltip: {
          shared: true,
          intersect: false
        },
        xaxis: {
          categories: probationStuOutChartMap.dateList1,
        },yaxis: [{
			/*title: {
				text: '학사경고',
				style: {
	            	fontSize: '18px',
          		}
			},*/}, {
			opposite: true,
			/*title: {
				text: '제적',
				style: {
	            	fontSize: '18px',
          		}
			}*/
		}]
	 };

    var chart = new ApexCharts(document.querySelector("#probationChart"), options);
    chart.render();
      
	

	
}

