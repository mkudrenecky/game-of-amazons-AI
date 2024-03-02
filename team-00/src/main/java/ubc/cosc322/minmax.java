package ubc.cosc322;

public class minmax {
    /*MINIMAX(s) =
UTILITY(s, MAX) if IS-TERMINAL(s)
maxa∈Actions(s) MINIMAX(RESULT(s, a)) if TO-MOVE(s)= MAX
mina∈Actions(s) MINIMAX(RESULT(s, a)) if TO-MOVE(s)= MIN 

function MINIMAX-SEARCH(game, state) returns an action
player←game.TO-MOVE(state)
value, move←MAX-VALUE(game, state)
return move
function MAX-VALUE(game, state) returns a (utility, move) pair
if game.IS-TERMINAL(state) then return game.UTILITY(state, player), null
v←−∞
for each a in game.ACTIONS(state) do
v2, a2←MIN-VALUE(game, game.RESULT(state, a))
if v2 > v then
v, move←v2, a
return v, move
function MIN-VALUE(game, state) returns a (utility, move) pair
if game.IS-TERMINAL(state) then return game.UTILITY(state, player), null
v←+∞
for each a in game.ACTIONS(state) do
v2, a2←MAX-VALUE(game, game.RESULT(state, a))
if v2 < v then
v, move←v2, a
return v, move
*/
    
}
