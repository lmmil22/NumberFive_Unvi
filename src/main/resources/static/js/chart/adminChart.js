 var options = {
          series: [{
          name: '접수내역',
          type: 'column',
          data: [10, 20, 30, 40, 50, 60, 70]
        }, {
          name: '승인내역',
          type: 'line',
          data: [10, 20, 30, 40, 50, 60, 70]
        }],
          chart: {
          height: 350,
          type: 'line',
        },
        stroke: {
          width: [0, 4]
        },
        title: {
          text: '학적 변경 신청 승인 현황'
        },
        dataLabels: {
          enabled: true,
          enabledOnSeries: [1]
        },
        labels: ['2022.01', '2022.02', '2022.03', '2022.04', '2022.05', '2022.06', '2022.07', '2022.08', '2022.09', '2022.10', '2022.11', '2022.12'],
        xaxis: {
          type: 'datetime'
        },
        yaxis: [{
          title: {
            text: 'Website Blog',
          },
        
        }, {
          opposite: true,
          title: {
            text: 'Social Media'
          }
        }]
        };

        var chart = new ApexCharts(document.querySelector("#chart"), options);
        chart.render();
      
      
    