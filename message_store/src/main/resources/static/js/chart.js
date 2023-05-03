const ctx = document.getElementById('myChart').getContext('2d');
let chartData = generateChart();

chartData.done(function(data) {
    console.log(data);

    const myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: data.labels,
            datasets: [{
                data: data.allPost,
                label: "All Reaction",
                borderColor: "#3e95cd",
                fill: false
            }, {
                data: data.likePost,
                label: "Like Reaction",
                borderColor: "#3cba9f",
                fill: false
            }, {
                data: data.dislikePost,
                label: "Dislike Reaction",
                borderColor: "#c45850",
                fill: false
            }
            ]
        },
        options: {
            title: {
                display: true,
                text: 'Post reaction representation'
            }
        }
    });

});

function generateChart() {

    return jQuery.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/personal/dashboard/chart",
        success: function (result) {
            return result;
        },
        error: function () {
            return "error";
        }
    });
}
