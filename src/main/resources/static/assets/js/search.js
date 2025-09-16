// 검색하기
const input = document.getElementById("text");

const btn = document.getElementById("searchBtn");

const request = () => {
	
	let url = "search";
	
	let text = input.value;
	
	fetch( url + "?text=" + text, { method:"GET"} )
		 .then(res=>res.json())
		 .then(data=>{
		    console.log(data);

            const tbody = document.querySelector("tbody");

            tbody.innerHTML = "";

            for(let i = 0; i < data.length; i++){

                let board = data[i];

                tbody.innerHTML += `
                    <tr>
                        <td>${board.idx}</td>
                        <td><a href="view?idx=${board.idx}">${board.title}</a></td>
                        <td>${board.writer}</td>
                        <td>${board.indate }</td>
                        <td> <a href="delete/${board.idx }" > X </a> </td>
                    </tr>
                `;


            }
		 })
		 .catch(err=>{ console.log(err); });
	
	
}

btn.addEventListener("click", request );


// =======================================

let req = "chart";

fetch(req, { method:"GET" })
.then(res=>res.json())
.then(data=>{

    console.log('그래프데이터:',data);

    let writer = [];
    let count = [];

    for(let i = 0; i < data.length; i++){
        writer.push( data[i].writer );
        count.push( data[i].count );
    }



    const ctx = document.getElementById('myChart');

      new Chart(ctx, {
        type: 'line',
        data: {
          labels: writer,
          datasets: [{
            label: 'count',
            data: count,
            borderWidth: 1,
            backgroundColor : 'red'
          },
            {
            label: 'count',
            data: count,
            borderWidth: 5,
            backgroundColor : 'yellow'
          }


          ]
        },
        options: {
          scales: {
            y: {
              beginAtZero: true
            }
          }
        }
      });
})
.catch(err=>{ console.log(err); });


























