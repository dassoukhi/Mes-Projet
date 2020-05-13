import calendar
import requests
import pandas as pd
from SMS import SMS
from copy import deepcopy
from bs4 import BeautifulSoup
import time
from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException
import datetime
from datetime import timedelta as td



def isOkeyMatch(game):
    isfinish = game.find("div", class_="status_name")
    probability = game.find("div", class_="godds")
    probability = probability.get_text()
    probability = probability.rsplit()
    if isfinish.get_text() != "Not started" or probability[0] == '-':
        return False
    return True
def isFinishMatch(game):
    isfinish = game.find("div", class_="status_name")
    probability = game.find("div", class_="godds")
    probability = probability.get_text()
    probability = probability.rsplit()
    if (isfinish.get_text() == "Finished" or isfinish.get_text() == "After penalty") and probability[0] != '-':
        return True
    return False

def match():

    URL = 'https://www.fctables.com/livescore/'

    headers = {
        "user-Agent": 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36'
    }

    page = requests.get(URL, headers=headers)

    soup = BeautifulSoup(page.content, 'html.parser')
    title = soup.findAll("div", class_="league" ,limit=None)
    # lastGameForCombi = soup.find("div", id="livescore")
    # print(lastGameForCombi)
    bigListe=[]
    for league in title:
        allGame = league.findAll(attrs={"itemtype":"https://schema.org/Event"})
        for game in allGame:
            check = game
            if isOkeyMatch(check):
                heure = game.find(attrs={"data-only-hour":"1"})
                formatHeure = heure.get('title')
                equipe_1=game.find("span", class_="home text-right truncate")
                equipe_1 = enleveCrochet(equipe_1.get_text())
                equipe_2=game.find("span", class_="away truncate")
                equipe_2 = enleveCrochet(equipe_2.get_text())
                equipe_2 = equipe_2.strip()
                heure = heure.get_text()
                ajoutHeure=heure[0]+heure[1]
                ajoutHeure = int(ajoutHeure)+1
                if ((int(ajoutHeure)+1) < 22) or (((int(ajoutHeure)+1) == 22) and ((int(heure[3])==0))):
                    ajoutHeure = str(ajoutHeure)+heure[2]+heure[3]+heure[4]
                    cotes = game.find("div", class_="godds")
                    proba = cotes.findAll("div")
                    listeP = 3*[0]
                    i = 0
                    for p in proba:
                        listeP[i] = int(p.get("data-p"))
                        i=i+1
                    cotes = cotes.get_text()
                    cotes = cotes.rsplit()
                    infoListe=[]
                    infoListe.append((equipe_1+"-"+equipe_2).rsplit("-"))
                    infoListe.append(cotes)
                    infoListe.append(listeP)
                    infoListe.append(ajoutHeure.rsplit(":"))
                    infoListe[3][0] = int(infoListe[3][0])
                    infoListe[3][1] = int(infoListe[3][1])
                    infoListe[1][0] = float( infoListe[1][0])
                    infoListe[1][1] = float(infoListe[1][1])
                    infoListe[1][2] = float(infoListe[1][2])
                    bigListe.append(infoListe)
    return bigListe

def afficheMatch(liste):
    for foot in liste:
        if foot[-1][1] < 10:
            min = str(foot[-1][1])+"0"
        else:  min = str(foot[-1][1])
        if foot[-1][0] < 10:
            heure = str(foot[-1][0])+"0"
        else: heure = str(foot[-1][0])
        print(foot[0][0]+" Vs "+foot[0][1]+" à "+heure+":"+min)
        print(foot[1])
        print("["+str(foot[2][0])+"%, "+str(foot[2][1])+"% , "+str(foot[2][2])+"%]")



def enleveCrochet(chaine):
    c = '['
    if chaine[0]==c :
        chaine = chaine[1:]
        while chaine[0]!=']':
            chaine = chaine[1:]

        chaine = chaine[1:]



    c = ']'
    if chaine[-1] == c:
        chaine = chaine[:-1]
        while chaine[-1] != '[':
            chaine = chaine[:-1]
        chaine = chaine[:-1]


    return chaine


def premierMatch(minHeure, minMinute, foot):
    duree = td(hours=minHeure, minutes=minMinute, seconds=59) - td(hours=foot[-1][0], minutes=foot[-1][1], seconds=0)
    if duree.total_seconds() > 0:
        minHeure = foot[-1][0]
        minMinute = foot[-1][1]
    return minHeure,minMinute
def dernierMatch(maxHeure,maxMinute,foot):
    duree = td(hours=maxHeure, minutes=maxMinute, seconds=0) - td(hours=foot[-1][0], minutes=foot[-1][1], seconds=0)
    if duree.total_seconds() < 0:
        maxHeure = foot[-1][0]
        maxMinute = foot[-1][1]
    return maxHeure,maxMinute
def combiner(liste):
    message = ""
    cote = 1.0
    maxHeure=1
    maxMinute=0
    minHeure = 23
    minMinute = 59
    lastGameForCombi = []
    listeCombi = []
    listeBonCombi = []
    totalMatch = 0
    pm=[]
    for foot in liste:
        hv = foot[2][0]
        nn = foot[2][1]
        exv= foot[2][2]

        if hv >= 60 and exv<=20:
            minHeure,minMinute=premierMatch(minHeure, minMinute, foot)
            maxHeure,maxMinute=dernierMatch(maxHeure, maxMinute, foot)
            message+=foot[0][0]+" Vs "+foot[0][1]+"\n"
            message+="[1 , * , * ] \n"
            message+="["+str(foot[2][0])+"%, "+str(foot[2][1])+"% , "+str(foot[2][2])+"%]"+"\n"
            message+="-------------\n"
            cote *=foot[1][0]
            totalMatch = totalMatch + 1
            tmp = deepcopy(foot)[:-1]
            tmp[1][0] = "1"
            tmp[1][1] = "*"
            tmp[1][2] = "*"
            listeCombi.append(tmp)


        elif hv <= 20 and exv>=60:
            minHeure,minMinute=premierMatch(minHeure, minMinute, foot)
            maxHeure,maxMinute=dernierMatch(maxHeure, maxMinute, foot)
            message += foot[0][0] + " Vs " + foot[0][1]+"\n"
            message += "[* , * , 1 ] \n"
            message += "["+str(foot[2][0])+"%, "+str(foot[2][1])+"% , "+str(foot[2][2])+"%]"+"\n"
            message += "-------------\n"
            cote *= foot[1][2]
            totalMatch = totalMatch + 1
            tmp = deepcopy(foot)[:-1]
            tmp[1][0]="*"
            tmp[1][1]="*"
            tmp[1][2]="1"
            listeCombi.append(tmp)
        elif abs((hv - exv)) <= 6 :
            minHeure, minMinute = premierMatch(minHeure, minMinute, foot)
            maxHeure, maxMinute = dernierMatch(maxHeure, maxMinute, foot)
            message += foot[0][0] + " Vs " + foot[0][1] + "\n"
            message += "[m , * , m ] \n"
            message += "[" + str(foot[2][0]) + "%, " + str(foot[2][1]) + "% , " + str(foot[2][2]) + "%]" + "\n"
            message += "-------------\n"
            cote *= foot[1][1]
            totalMatch = totalMatch + 1
            tmp = deepcopy(foot)[:-1]
            tmp[1][0] = "m"
            tmp[1][1] = "*"
            tmp[1][2] = "m"
            listeCombi.append(tmp)



    cote = round(cote,2)
    message+="cotes Total = "+str(cote)+"\n"
    message += "Total Match = " + str(totalMatch) +"\n"
    message += "First Match commence à :" + str(minHeure) + "h" + str(minMinute) +"\n"
    message+="Dernier Match commence à :"+str(maxHeure)+"h"+str(maxMinute)
    print(listeCombi)
    SMS(message)
    return listeCombi,maxHeure,maxMinute

def estInteressant(liste):
    if liste[1][0] == '1' and (liste[2][0] - (liste[2][1] + liste[2][2])) <=25:
        return True
    elif liste[1][2] == '1' and (liste[2][2] - (liste[2][1] + liste[2][0])) <=25:
        return True
    else: return False
def combiReformer(liste):
    cpt = 0
    nouveau = []
    message =""

    if len(liste) < 2:
        return 0
    for i in range(len(liste)):
        l = []
        if estInteressant(liste[i]):
            l.append(liste[i][0])
            l.append(['m','*','m'])
            l.append(liste[i][2])
            cpt = 1
            message += liste[i][0][0] + " Vs " + liste[i][0][1] + "\n"
            message +="[m ,* ,m]\n"
            message +="------------\n"
        else:
            l = deepcopy(liste[i])
            message += liste[i][0][0] + " Vs " + liste[i][0][1] + "\n"
            message += "["+str(liste[i][1][0])+", "+str(liste[i][1][1])+", "+str(liste[i][1][2])+"]\n"
            message += "------------\n"

        nouveau.append(l)
    if cpt == 0:
        return 0
    else:
        SMS(message)
        return nouveau


def heureDuRecolte():
    test = datetime.datetime.now()
    annee = test.year
    mois = test.month
    jour = test.day
    he = 23
    min = 59
    sec = 59
    plus = 10 * 60 * 60
    if test.hour >=0 and test.hour <10:
        return ((10 - test.hour)*60*60) - ((test.minute)*60)
    av = datetime.datetime(annee, mois, jour, he, min, sec)
    recolte = (av - test).total_seconds()
    if recolte > 0 :
        return recolte+plus
    else:
        return  0

def heureDuCheck(heure, minute):
    a = datetime.datetime.now()
    jour = a.day
    heure = heure + 2
    mois = a.month
    annee = a.year
    balek, nombreDeJour = calendar.monthrange(annee, mois)


    if heure >=24:
        heure=heure%24
        jour=jour+1
        if jour == nombreDeJour :
            jour = 1
            mois = mois + 1
            if mois == 12 :
                mois = 1
                annee = annee+1

    b = datetime.datetime(annee, mois, jour, heure, minute, 0)
    return (b - a).total_seconds()

def resultatMatch():
    URL = 'https://www.fctables.com/livescore/'

    headers = {
        "user-Agent": 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36'
    }

    page = requests.get(URL, headers=headers)

    soup = BeautifulSoup(page.content, 'html.parser')
    title = soup.findAll("div", class_="league", limit=None)

    listeResultat = []
    for league in title:
        allGame = league.findAll(attrs={"itemtype": "https://schema.org/Event"})

        for game in allGame:
            listeM = []
            check = game
            if isFinishMatch(check):
                equipe_1 = game.find("span", class_="home text-right truncate")
                if equipe_1 == None:
                    equipe_1 = game.find("span", class_="home text-right truncate win")
                equipe_2 = game.find("span", class_="away truncate")
                if equipe_2 == None:
                     equipe_2 = game.find("span", class_="away truncate win")
                equipe_1 = equipe_1.get_text()
                equipe_1 = equipe_1.strip()
                equipe_1 = enleveCrochet(equipe_1)
                equipe_2 = equipe_2.get_text()
                equipe_2 = equipe_2.strip()
                equipe_2 = enleveCrochet(equipe_2)
                listeM.append((equipe_1+"-"+equipe_2).rsplit("-"))
                score = game.find("span", class_="score text-center")
                score = str(score.get_text())
                score = score.strip()
                score=score.rsplit(("-"))
                score[0]=int(score[0])
                score[1] = int(score[1])
                listeM.append(score)
                listeResultat.append(listeM)
    return  listeResultat

def miseSur(resM):
   if resM[0]=='1':
       return 'v';
   elif resM[0]=='-':
       return 'n'
   elif resM[2]=='1':
       return 'p'
   elif resM[0]=='m':
       return  'm'



def checkCombi(resultat, combi):
    tailleCombi = len(combi)
    i = 0
    fini = 0
    message=""
    perdu =0
    gagner = 0
    notFinish = 0
    while i < len(combi):
        c = combi[i]
        find = 0
        for match in resultat:

            if c[0][0]==match[0][0]:
                find = 1
                fini = fini + 1
                message += c[0][0]+" "+str(match[1][0])+"-"+str(match[1][1])+" "+c[0][1]
                mise = miseSur(c[1])
                if match[1][0] > match[1][1] and mise=='v':
                    tailleCombi=tailleCombi-1
                    message+=" Ok\n"
                    gagner = gagner + 1
                elif match[1][0] < match[1][1] and mise=='p':
                    tailleCombi = tailleCombi - 1
                    message += " Ok\n"
                    gagner = gagner + 1
                elif match[1][0] == match[1][1] and mise=='n':
                    tailleCombi = tailleCombi - 1
                    message += " Ok\n"
                    gagner = gagner + 1
                elif match[1][0] > 0 and  match[1][1] > 0 and mise=='m':
                    tailleCombi = tailleCombi - 1
                    message += " Ok\n"
                    gagner = gagner + 1
                else:
                    message += "-X-\n"
                    perdu = perdu + 1
                message += "----------------\n"

        if find == 0:
            notFinish = notFinish + 1
            message += c[0][0] + " Vs " + c[0][1] + "-PF-"

        i=i+1
    if tailleCombi==0 :
        message+="\n Combi GAGNEE"
        SMS(message)
        return "GAGNEE"
    elif fini == len(combi):
        message += "\n Combi PERDU\n"
        message +="Match gagner : "+ str(gagner)+"\n"
        message += "Match perdu : " + str(perdu)
        SMS(message)
        return "PERDU"
    else:
        message += "Match gagner : " + str(gagner) + "\n"
        message += "Match perdu : " + str(perdu)+ "\n"
        message += "Match Non Fini : " + str(notFinish)
        message += "\nAu moins un match pas fini\n"
        SMS(message)
        return "PAS FINI"





def monDate():

        date = datetime.datetime.now()

        jour=""
        if date.weekday() == 0:
            jour="Lundi"
        if date.weekday() == 1:
            jour="Mardi"
        if date.weekday() == 2:
            jour="Mercredi"
        if date.weekday() == 3:
            jour="Jeudi"
        if date.weekday() == 4:
            jour="Vendredi"
        if date.weekday() == 5:
            jour="Samedi"
        if date.weekday() == 6:
            jour="Dimanche"

        if date.day < 10:
            day = "0"+str(date.day)
        else:
            str(date.day)

        if date.day < 10:
            mois = "0"+str(date.month)
        else:
           mois= str(date.month)

        today="******************"+jour+", le "+day+"/"+mois+"/"+str(date.year)+"****************"

        print(today)




while(True):

    recolte = heureDuRecolte()
    print("heure du recolte :", recolte/(3600))
    #time.sleep(recolte)
    liste = match()
    afficheMatch(liste)
    combi,heureC,minC = combiner(deepcopy(liste))
    reforme = combiReformer(deepcopy(combi))
    print("heure du check", heureDuCheck(heureC, minC) / (3600), "en heure")
    if heureDuCheck(heureC, minC) < 0:
        k = 0
    else:
        k = heureDuCheck(heureC, minC)
    if len(combi) > 0:
        time.sleep(k)
        time.sleep(60 * 15)
   
        resultat = resultatMatch()
        print(checkCombi(resultat , combi))
        if reforme != 0:
            print(checkCombi(resultat,reforme))











