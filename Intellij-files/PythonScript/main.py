import sys

import requests
from bs4 import BeautifulSoup


def build_team_string(playerLine):
    """ This function takes the text that is sent to it and formats it based on if it is a name(1) or other(2)"""

    #Strip the string so that there are no spaces before or after
    playerLine.strip()
    stringBuilder = playerLine + "\t"
    return stringBuilder

def getPlayersYear():
    #Open the file to which the program will write to
    playa = open("players.txt", "w", encoding="utf-8")

    #This is the URL that the program will take the html code from
    URL = "https://www.basketball-reference.com/leagues/NBA_2002_per_game.html"
    #URL = "https://www.basketball-reference.com/leagues/NBA_" + sys.argv[2] + "_per_game.html"
    #Takes the URL and requests the html code
    page = requests.get(URL)

    #Pull the html code, so it can be parsed later in the program
    soup = BeautifulSoup(page.content, "html.parser")
    #Eliminate some html code to just what is needed
    results = soup.find(id="per_game_stats")
    #Further eliminate html code to what is needed
    playerStats = results.find_all("tr", class_="full_table")


    #go through the html code that is in the variable playerStats
    for playerStat in playerStats:
        #This variable is used to build the string for the player's stats
        playerLine = ""

        #Get the player's name from the html code
        playerName = playerStat.find("td", class_="left")
        #call the function to format the player's name
        playerLine = build_team_string(playerName.text)

        #Get the player's position
        playerPosition = playerStat.find("td", class_="center")
        playerLine += build_team_string(playerPosition.text)

        playerAge = playerStat.find("td", class_="right")
        playerLine += build_team_string(playerAge.text)

        #Pull each line of the html code that contains the class 'left'
        playerTableLine = playerStat.find_all("td", class_="left")
        for team in playerTableLine:
            #The if statement is to exclude the player's name, as it is part of the class 'left' but needs to come before
            #other strings
            if playerName.text == team.text:
                continue
            playerLine += build_team_string(team.text)

        #Pull each line of the html code that contains the class 'right'
        allPlayersStats = playerStat.find_all("td", class_="right")
        i = 0
        for stats in allPlayersStats:
            if i == 0:
                i += 1
                continue
            #The if statement is to exclude the player's age, as it is part of the class 'right'
            gamesPlayed = stats.text
            playerLine += build_team_string(gamesPlayed)


        #Write each completed string to the file to build the player database
        playa.write(playerLine + "\n")


if __name__ == '__main__':
    getPlayersYear()

