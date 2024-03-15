# Game of the Amazons AI 
An ongoing implementation of an aritificial intelligence based Game of Amazons player.

## Overview
The game playing agent leverages the Minmax backtracking algorithm with alpha beta pruning to build and search the game tree coupled with iterative deepening to allow the best search under the time constraints. Each player has a 30s time limit to make a move. We have implemented a variety of heuristics to evaluate the quality of the moves. Current evaluation heuristics include mobility and territory, with a combined heuristic on the horizon. We have included a move validator to detect cheating opponents. We have implemented the ability to play the game in the command line through a test class, but the GUI is not available out of the box as it relies on a UBC hosted server (VPN access required).

## Demo
The demo displays Black black playing the Minmax algorithm with moblilty heuristic (with search tree depth of 1 for visualization speed purposes) against White, playing random moves.

![amazons_demo](https://github.com/mkudrenecky/game-of-amazons-AI/assets/112984152/62c09bb2-08e5-47e9-9315-88c5b353e726)

## Optimizations going forward
- [x] bit representation of game state to reduce memory load
- [ ] improved heuristics to better model game outcomes
- [ ] move ordering to optimize search tree
- [ ] negaMax algorithm

## Tech
![](https://skills.thijs.gg/icons?i=java)
