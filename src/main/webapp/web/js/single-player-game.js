const statusDisplay = document.querySelector('.game-status');
let gameActive = true;
let currentPlayer = "X";
let gameState =
    [
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "]
    ];
let startCounter = 0;
let sidesSelected = false;
const winningMessage = () => `Player ${currentPlayer} has won!`;
const currentPlayerTurn = () => `It's ${currentPlayer}'s turn`;

handleStatusDisplay();

function handleCellPlayed(clickedCell, clickedCellRow, clickedCellColumn) {
    gameState[clickedCellRow][clickedCellColumn] = currentPlayer;
    clickedCell.innerHTML = currentPlayer;
}

function handleStatusDisplay(){
    if (startCounter === -1){
        if (sidesSelected === false){

            document.getElementById("sideCellX").style.visibility="visible";
            document.getElementById("sideCellY").style.visibility="visible";
            document.getElementById("sideSelectContainer").style.visibility="visible";

            statusDisplay.innerHTML = 'Please select your side.';

            document.querySelectorAll('.sideCell').forEach(sideCell =>
                sideCell.addEventListener('click', handleSideCellClick));

        } else {
            statusDisplay.innerHTML = currentPlayerTurn();
        }
    } else {
        statusDisplay.innerHTML = `Select ${currentPlayer} cell.`;
    }
}

function handleSideCellClick(clickedCellEvent){
    const clickedSideCell = clickedCellEvent.target;
    sidesSelected = true;

}

function handlePlayerChange() {
    currentPlayer = currentPlayer === "X" ? "O" : "X";
    handleStatusDisplay();
    switch (startCounter){
        case 0:
            startCounter = 1;
            break;
        default:
            startCounter = -1;
            sidesSelected = true;
    }
}

function handleResultValidation(clickedCellRow, clickedCellColumn) {
    let counter = 0;

    for (let i = clickedCellColumn; i < clickedCellColumn+5; i++) { //LF straight line L-R
        if (i >= gameState.length){
            break;
        }
        else if (gameState[clickedCellRow][i] === currentPlayer){
            counter++;
        } else {
            break;
        }
    }

    for (let i = clickedCellColumn; i > clickedCellColumn-5; i--) { //LF straight line R-L
        if (i <= -1){
            break;
        } else if (gameState[clickedCellRow][i] === currentPlayer){
            counter++;
        } else {
            break;
        }
    }

    if (counter > 5){
        statusDisplay.innerHTML = winningMessage();
        gameActive = false;
        return;
    }

    counter = 0;

    for (let i = clickedCellRow; i < clickedCellRow+5; i++) { //LF straight line T to B
        if (i >= gameState.length){
            break;
        }
        else if (gameState[i][clickedCellColumn] === currentPlayer){
            counter++;
        } else {
            break;
        }
    }

    for (let i = clickedCellRow; i > clickedCellRow-5; i--) { //LF straight line B to T
        if (i <= -1){
            break;
        } else if (gameState[i][clickedCellColumn] === currentPlayer){
            counter++;
        } else {
            break;
        }
    }

    if (counter > 5){
        statusDisplay.innerHTML = winningMessage();
        gameActive = false;
        return;
    }

    counter = 0;

    let tmpCol = clickedCellColumn;
    for (let i = clickedCellRow; i < clickedCellRow+5; i++) { //LF diagonal L to R and B to T
        if (i >= gameState.length || tmpCol >= gameState.length){
            break;
        } else if (gameState[i][tmpCol] === currentPlayer) {
            counter++;
            tmpCol++;
        } else {
            break;
        }
    }

    tmpCol = clickedCellColumn;
    for (let i = clickedCellRow; i > clickedCellRow-5; i--) { //LF diagonal R to L and T to B
        if (i <= -1 || tmpCol <= -1){
            break;
        } else if (gameState[i][tmpCol] === currentPlayer) {
            counter++;
            tmpCol--;
        } else {
            break;
        }
    }

    if (counter > 5){
        statusDisplay.innerHTML = winningMessage();
        gameActive = false;
        return;
    }

    counter = 0;

    tmpCol = clickedCellColumn;
    for (let i = clickedCellRow; i < clickedCellRow+5; i++) {
        if (i >= gameState.length || tmpCol <= -1){
            break;
        } else if (gameState[i][tmpCol] === currentPlayer){
            counter++;
            tmpCol--;
        } else {
            break;
        }
    }

    tmpCol = clickedCellColumn;
    for (let i = clickedCellRow; i > clickedCellRow-5; i--) { //LF diagonal R to L and B to T
        if (i <= -1 || tmpCol >= gameState.length){
            break;
        }
        else if (gameState[i][tmpCol] === currentPlayer){
            counter++;
            tmpCol++;
        } else {
            break;
        }
    }

    if (counter > 5){
        statusDisplay.innerHTML = winningMessage();
        gameActive = false;
        return;
    }

    handlePlayerChange();
}

function handleCellClick(clickedCellEvent) {
    const clickedCell = clickedCellEvent.target;

    const clickedCellRow = parseInt(clickedCell.getAttribute('cell-row'));
    const clickedCellColumn = parseInt(clickedCell.getAttribute('cell-column'));

    if (gameState[clickedCellRow][clickedCellColumn] !== " " || !gameActive) {
        return;
    }
    handleCellPlayed(clickedCell, clickedCellRow, clickedCellColumn);
    handleResultValidation(clickedCellRow, clickedCellColumn);

}

function handleRestartGame() {
    gameActive = true;
    currentPlayer = "X";
    gameState =
        [
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "]
        ];
    statusDisplay.innerHTML = currentPlayerTurn();
    document.querySelectorAll('.cell').forEach(cell => cell.innerHTML = " ");
}

document.querySelectorAll('.cell').forEach(cell => cell.addEventListener('click', handleCellClick));
document.getElementById('restartGameBtn').addEventListener('click', handleRestartGame);