# Game

## Square

    A Square:
    - represents one block of the 8x8 grid
    - should have an x and y coordinate
    - should have a piece variable associated with it - can be empty or an actual piece

## Piece

    A Piece:
    - Abstract class
    - Every piece will be placed on a square
    - Each piece type will be an extended class which implements the abstract methods
    - Should have colour associated with it
    - Should check whther the piece is taken or not

## Board

    A Board:
    - 8x8 matrix of squares, containing active pieces
    - initialize the board

## Player

    A Player:
    - will be a participant in a game
    - can have their own profile
    - previous games
    - winrate
    - what colour they are

## Movement

    A move:
    - contains start and ending coords
    - move keeps track of player who made the move
    - has possible move for castling
    - has possible move for en passant

## Game

    The game:
    - controls flow of game
    - keeps track of all game moves
    - keeps track of current player turn
    - final result of game
    - enum for game status

# Server

## Users

    A User:
    - Can signup
    - Can login
    - secure password
    - see previous games
    - winrate
    - email validation
