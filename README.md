### Endpoints
#### Create new roulette: 
 *POST :* localhost:8080/roulette
#### Roulette opening:
 *PUT :* localhost:8080/roulette/:roulette-id  
 *PATH-VARIABLE:* roulette-id
#### Bet on roulette: 
 *POST :* localhost:8080/roulette/bet/:roulette-id  
 *HEADER:*  
  - Content-Type: application/json  
  - user-id: 123
  
 *BODY:*    
  - {  
 "betOn":"string",  
 "amount":0  
 }  
 
 *PATH-VARIABLE:* roulette-id
#### Close the roulette
*GET :* localhost:8080/roulette/close-roulette/:roulette-id  
*PATH-VARIABLE:* roulette-id
#### Get all roulettes with id and state
*GET :* localhost:8080/roulette
 
 
 