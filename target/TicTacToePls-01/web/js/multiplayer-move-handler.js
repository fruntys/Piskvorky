function showCellClick(clickedCellEvent){
    const clickedCell = clickedCellEvent.target;

    const clickedCellRow = clickedCell.getAttribute("row")
    const clickedCellCol = clickedCell.getAttribute("column")

    console.log("CLICK: " + clickedCellRow + ", " + clickedCellCol);

    document.getElementById("playCheck").innerHTML = clickedCellRow + ", " + clickedCellCol;
    document.getElementById("input_cellRow").setAttribute('value', clickedCellRow);
    document.getElementById("input_cellCol").setAttribute('value', clickedCellCol);
}

document.querySelectorAll('th').forEach(cell => cell.addEventListener('click', showCellClick));