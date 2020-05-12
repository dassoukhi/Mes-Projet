from copy import deepcopy
import urllib.request, json, unidecode
import praw
import pandas as pd

import json
import nltk
from nltk.corpus import treebank
import copy
import re
import urllib
import requests
import webbrowser
from nltk.corpus import ieer
import math



limit = 300

def addInUsa(text):
    text =text+" -> 1\n"
    print(text)
    with open("USA.rd","a") as file1:
        file1.write(text)
        file1.close()

def addInOthers(text):
    text = text+" -> 0\n"
    print(text)
    with open("Others.rd","a") as file2:
        file2.write(text)
        file2.close()


def isClean(title):
    title=str(title)
    if title.find("x") >= 0:
        k= title.find("x")
        if (k-1) >=0 and (not title[k-1].isalpha()):
            return False
        elif (k+1) < len(title) and ( not title[k+1].isalpha()):
            return False
    elif title.find("X") >= 0:
        k= title.find("X")
        if (k-1) >=0 and (not title[k-1].isalpha()):
            return False
        elif (k+1) < len(title) and (not title[k+1].isalpha()):
            return False

    elif title.find("×") >= 0:
        k= title.find("×")
        if (k-1) >=0 and (not title[k-1].isalpha()):
            return False
        elif (k+1) < len(title) and (not title[k+1].isalpha()):
            return False

    elif title.find("*") >= 0:
            k = title.find("*")
            if (k - 1) >= 0 and (not title[k - 1].isalpha()):
                return False
            elif (k + 1) < len(title) and (not title[k + 1].isalpha()):
                return False
    return True

def properNoun(lexical):
    noun =""

    for i in range(len(lexical)):
        if lexical[i][1]=="NNP" and str(lexical[i][0]).isalpha():
            noun+=lexical[i][0]+" "
        elif (i+1) < len(lexical) and str(lexical[i][0]).isalpha() and lexical[i-1][1]=="NNP" and lexical[i+1][1]=="NNP":
            noun += lexical[i][0] + " "
    if noun=="":
        for i in range(len(lexical)):
            if (lexical[i][1] == "NN" or lexical[i][1] == "NNS") and str(lexical[i][0]).isalpha() and str(lexical[i][0])[0].isupper():
                noun += lexical[i][0] + " "
            elif ((i + 1) < len(lexical) and (i-1) >=0) and lexical[i][1]!="IN" and str(lexical[i][0]).isalpha() and ((lexical[i-1][1] == "NN" and lexical[i + 1][1] == "NN") or (lexical[i-1][1] == "NNS" and lexical[i + 1][1] == "NNS")):
                noun += lexical[i][0] + " "

    return noun.strip()

def locateFormed(location):
    list = ""
    for triplet in location:
        list += triplet[0][0]+" "
    return list.strip()
def cleanTitle(title, step):
    if step==1:
        title = str(title)
        title = title.strip()

        motif = re.compile(r"\d{1,}")
        list = motif.findall(title)
        if len(list)>0:
            for out in list:
                title = title.replace(out,"")
        indice = title.find("IG")
        if indice >= 0:
            if (indice - 1) >= 0  and not title[indice - 1].isalpha():
                title = title.replace("IG", "")
        indice = title.find("ig")
        if indice >= 0:
            if (indice - 1) >= 0 and not title[indice - 1].isalpha() :
                title = title.replace("ig", "")

        motif = re.compile(r"[a-zA-Z]+[0-9]+")
        list = motif.findall(title)
        if len(list) > 0:
            for out in list:
                title = title.replace(out, "")

        title = title.replace("😉", "")
        title = title.replace("️","")
        title = title.replace("❤", "")
        title=title.replace("[OC]","")
        title = title.replace("{OC}", "")
        title = title.replace("{oc}", "")
        title = title.replace(",OC ", "")
        title = title.replace("OC, ", "")
        title = title.replace("(OC)", "")
        title = title.replace("(oc), ", "")
        title = title.replace("oc,", "")
        title = title.replace(",oc ", "")
        title = title.replace(":", "")
        title = title.replace("[oc]", "")
        title = title.replace("(", "")
        title = title.replace(")", "")
        title = title.replace("[", "")
        title = title.replace("]", "")
        title=title.replace(" OC ","")
        title = title.replace(" oc ", "")
        indice = title.find("OC")
        if indice >= 0 and (indice+2)== len(title):
            title = title.replace("OC", "")
        indice = title.find("oc")
        if indice >= 0 and (indice + 2) == len(title):
            title = title.replace("oc", "")




        if title[:3]=="OC " or title[:3]=="oc ":
            title = title[3:]
        title = title.strip()
        if (title.find("@") >= 0):
            indice = title.find("@")
            k = indice + 1
            print(" avant",title)
            while ( k < len(title) and title[k]!=" " ):
                k = k + 1
            title = title[:indice] + title[k:]
            title = title.strip()


        title = title.strip()

        print("After clean :",title)
        return title
    if step==2:

        indice = title.find("x")
        if indice < 0:
            indice = title.find("X")
            if indice < 0:
                indice = title.find("×")
                if indice < 0:
                    indice = title.find("*")

        if indice >=0 and ((indice-1)>=0 and not title[indice-1].isalpha()) and ((indice+1)<len(title) and not title[indice+1].isalpha()):
            chain1 = str(title[:indice])
            chain2 = str(title[indice+1:])
            if len(chain1) > 0:
                while len(chain1)>0 and not chain1[-1].isalpha():
                    chain1 = chain1[:-1]
                while len(chain1)>0 and not chain1[0].isalpha():
                    chain1 = chain1[1:]
            else:
                chain1 = ""

            if len(chain2) > 1:
                while len(chain2)>0 and not chain2[0].isalpha():
                    chain2 = chain2[1:]
                while len(chain2)>0 and not chain2[-1].isalpha():
                    chain2 = chain2[:-1]
            else:
                chain2 = ""

            title = chain1+" "+chain2
            while len(title)>0 and not title[-1].isalpha():
                title = title[:-1]
            title = title.strip()
            print("After clean :", title)
            return title
        elif (indice + 1) == len(title) and (indice-1)>= 0 and  not title[indice - 1].isalpha():
            title = title[:-2]
        elif (indice == 0) and (indice + 1 ) < len(title) and  not title[indice + 1].isalpha():
            title = title[1:]
        print("After clean :", title)

    return title

def distanceBetweenCordinates(coord1, coord2):
    coord1[0] = math.radians(coord1[0])
    coord1[1] = math.radians(coord1[1])
    coord2[0] = math.radians(coord2[0])
    coord2[1] = math.radians(coord2[1])
    SPHERE_TERRE = 6378137
    DIST_LON = (coord2[0] - coord1[0]) / 2
    DIST_LAT = (coord2[1] - coord1[1]) / 2
    ETAPE1 = (math.sin(DIST_LAT)**2) + math.cos(coord1[1]) * math.cos(coord2[1]) * (math.sin(DIST_LON)**2)
    ETAPE2 = 2 * math.atan2(math.sqrt(ETAPE1), math.sqrt(1 - ETAPE1))
    resultat = (SPHERE_TERRE * ETAPE2) / 1000
    return resultat

def existNameSocialNetwork(title):

    title = str(title)
    indice = title.find("Insta")
    if indice < 0:
        indice = title.find("Instagram")
        if indice < 0:
            indice = title.find("instagram")
            if indice < 0:
                indice = title.find("instaGram")
                if indice < 0:
                    indice = title.find("insta")
                    if indice < 0:
                        indice = title.find("Facebook")
                        if indice < 0:
                            indice = title.find("facebook")
                            if indice < 0:
                                indice = title.find("FaceBook")
                                if indice < 0:
                                    indice = title.find("Fb")
                                    if indice < 0:
                                        indice = title.find("fb")
                                        if indice < 0:
                                            indice = title.find("FB")


    if indice >= 0:
        indice = indice + 1
        a = title[:indice-1]
        while indice < len(title) and title[indice] != " ":
            indice = indice + 1
        title = a+title[indice:]
        return existNameSocialNetwork(title)
    return title
def geoNamesSearch(lieu):
    lieu = lieu.replace(" ", "+")       #remplace les espaces par des +
    lieu = lieu.replace("'", "+")       #remplace les apostrophes par des plus
    lieu = unidecode.unidecode(lieu)    #pour retirer les accents !attention tester les cédilles

    api = "http://api.geonames.org/searchJSON?q="+lieu+"&maxRows=1&username=projet_TER_reddit"
    #print(api)

    with urllib.request.urlopen(api) as url:
        data = json.loads(url.read().decode())
    #print("Taille:", len(data))
    if data['totalResultsCount'] == 0:
        return -1

    long = data['geonames'][0]['lng']
    lat  = data['geonames'][0]['lat']
    contry = ""
    code = ""
    name = ""
    if "countryName" in data['geonames'][0] and "countryCode" in data['geonames'][0]:
        contry = data['geonames'][0]["countryName"]
        code = data['geonames'][0]["countryCode"]
        name = data['geonames'][0]["name"]
    else:
        contry = data['geonames'][0]["fcodeName"]
        code = data['geonames'][0]["fcode"]
        name = data['geonames'][0]["name"]


    list=[]

    list.append(lieu)
    list.append(long)
    list.append(lat)
    list.append(contry)
    list.append(code)
    list.append(name)
    return list



def findLocation(liste):
    list = []
    for triple in liste:
        if triple[0][1]=="NNP"  and  (triple[1]=="GSP" or triple[1]=="GPE" or triple[1]=="ORGANIZATION" or triple[1]=="PERSON" or triple[1]=="S"):
            list.append(triple)
    return list

def equalsTextList(list1, list2):
    if len(list1)!=len(list2):
        return  False
    for i in range(len(list2)):
        if list1[i]!=list2[i][0][0]:
            return False
    return True

def middleCheck(list1, list2):

    #check ineteressant
    composed = locateFormed(list2)
    i = len(list1)-1
    while i > 0 :
        composed =list1[i][0][0]+" "+composed
        find = geoNamesSearch(composed)
        if find != -1:
            return find
        i= i-1

        # check du list2
    find = geoNamesSearch(locateFormed(list2))
    if find != -1:
        return find

        # check pour list1
    if len(list1) >= 2:
            find = geoNamesSearch(locateFormed(list1))
            if find != -1:
                return find

    #check dichotomique
    i = 0
    composed = ""
    for word in reversed(list1):
        composed = word[0][0] +" "+ composed+(" " if len(composed)>0 else "") + list2[i][0][0]
        i = i+1
        find = geoNamesSearch(composed)
        if find != -1:
            return  find

    while i < len(list2):
        composed  += " " + list2[i][0][0]
        i = i+1
        find = geoNamesSearch(locateFormed(composed))
        if find != -1:
            return find
    composed = ""
    for word in reversed(list2):
        composed+=word[0][0]
        find = geoNamesSearch(composed)
        composed +=" "
        if find != -1:
            return find
    return -1
def fusion():
    tab = []
    p = pd.read_json("reddit.json")
    taille = p.shape[1] + 1
    for i in p:
        tab.append([p[i].title, p[i].afterClean, p[i].subreddit, p[i].url, p[i].body, p[i].location, p[i].longitude,
                    p[i].latitude, p[i].contryName, p[i].contryCode, p[i].name])

    f = pd.read_json("fusion.json")
    for i in f:
        tab.append([f[i].title, f[i].afterClean, f[i].subreddit, f[i].url, f[i].body, f[i].location, f[i].longitude,
                    f[i].latitude, f[i].contryName,
                    f[i].contryCode, f[i].name])

    tab = pd.DataFrame(tab,
                       columns=['title', 'afterClean', 'subreddit', 'url', 'body', 'location', 'longitude', 'latitude',
                                'contryName', 'contryCode', 'name'])

    tab.to_json("reddit.json", orient='index')


def collectionFromReddit():
    ok = 0

    reddit = praw.Reddit ( client_id = 'tMRO7I9OYVnG7A' , client_secret = 'Ghudpyy79xN1dUv9-lIdRaiTswE' , user_agent = 'dassScraping' )
    posts = []
    ml_subreddit = reddit.subreddit('EarthPorn')
    for post in ml_subreddit.hot(limit=limit):
        print("Original title :",post.title)
        step = 1
        afterClean = existNameSocialNetwork(deepcopy(post.title))
        afterClean=cleanTitle(afterClean,step)
        print("It's cleaned ?",isClean(afterClean))
        while not isClean(afterClean) and step <=2:
            step = step + 1
            afterClean = cleanTitle(afterClean, step)
            print("It's cleaned ?", isClean(afterClean))
        lexical = nltk.word_tokenize(afterClean)
        lexical = nltk.pos_tag(lexical)
        print("lexical:",lexical)
        proper = properNoun(lexical)
        print("proper noun:", proper)
        if len(proper) > 0:
            resultat = nltk.ne_chunk(nltk.pos_tag(nltk.word_tokenize(afterClean)))
            print("Test :",resultat.pos())
            loc = findLocation(resultat.pos())
            print("Location: ",loc)
            coordinate = geoNamesSearch(proper)
            print("geoNames:", coordinate)
            ok = ok + 1
            check = True
            if coordinate == -1 :
                ok = ok - 1
                if not equalsTextList(proper.split(),loc) and len(loc)>0:
                    coordinate = geoNamesSearch(locateFormed(loc))
                    if coordinate != -1:
                        ok = ok + 1
                        check = False
                    print("geoNames:",coordinate)

                    if check:
                        debut = loc[:len(loc) // 2]
                        fin = loc[len(loc) // 2:]

                        coordinate = middleCheck(debut,fin)
                        if coordinate != -1:
                            ok = ok + 1
                        print("geonames:",coordinate)
                elif len(loc)>1:
                    debut = loc[:len(loc) // 2]
                    fin = loc[len(loc) // 2:]

                    coordinate = middleCheck(debut, fin)
                    if coordinate != -1:
                        ok = ok + 1
                    print("geonames:", coordinate)

        print("-------------------------------------")
        if coordinate!=-1 and len(coordinate)== 6:
            posts.append([str(post.title), afterClean, str(post.subreddit), ""+str(post.url), str(post.selftext).strip(' \\'),coordinate[0],float(coordinate[1]),float(coordinate[2]),coordinate[3],coordinate[4], coordinate[5]])

            # if coordinate[4] == "US":
            #     addInUsa(afterClean)

    posts = pd.DataFrame(posts,columns=['title', 'afterClean', 'subreddit', 'url', 'body','location','longitude','latitude','contryName','contryCode','name'])
    print(ok,"Trouvés sur",limit)
    posts.to_json("fusion.json", orient='index')
    fusion()

#collectionFromReddit()