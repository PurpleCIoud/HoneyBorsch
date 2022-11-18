# HoneyBorsch
### Credits
- **Deran** - Co-Founder - Card artist
- **Mike** - Co-Founder - Card artist
- **Ilja** - Main dev - Code wizard


### card artist notice
To create new cards use this format:
```
{
    "id": [int],
    "name": [string],
    "baseHealth": [int],
    "baseAttack": [int],
    "attackType": [one of the following:DIRECT,DOMINO,AOE,POISON,LINE,NONE],
    "image": [keep this one empty for now],
    "description": [string]  
},
```
and link them to [CardDef.json](src/main/resources/CardDef.json)  
Make sure to validate your json files before attempting to push into the main!
