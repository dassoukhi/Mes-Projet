import nltk
from scrappingV2 import cleanTitle, findLocation, locateFormed, geoNamesSearch
import re

phrase = input("Entrez une phrase : \n")
phrase = str(phrase)

#Segmentation de la phrase
phrase_tokens = nltk.word_tokenize(phrase)
print("\n La phrase, mot par mot \n")
print(phrase_tokens)

# Heuristique IN : verifie si le mot "in" est present dans la phrase et recupere l'indice
in_exist = False

for mot in phrase_tokens:
    if mot == "in":
        in_exist = True
        in_indice = phrase_tokens.index("in")

#Attribution des POS (classe grammaticale)
phrase_tagged = nltk.pos_tag(phrase_tokens)
print("\n Les mots est leur tag \n")
print(phrase_tagged)

# Heuristique IN : teste de cette heuristique, pas encore au point
if in_exist == True:
    if phrase_tagged[in_indice+1][1] == "NNP":
        print("in",in_indice)
        print("ICI")
        in_locate = nltk.ne_chunk(phrase_tagged[in_indice+1])
        print("test",in_locate[-1])
        if in_locate[0] == "GPE":
            combi = phrase_tokens.index("in")+1
            print("LA")

# Heuristique principale
else:
    # remplacement de "Mt" par "Mount"
    possibilityList = ["Mt","MT","mT","mt"]
    for i in possibilityList:
        indice = phrase.find(i)
        if indice >= 0:

            if indice==0 and (indice + 2) < len(phrase) and not phrase[indice + 2].isalpha() :
                phrase = phrase.replace(i+str(phrase[indice+2]),"Mount ")

            elif not phrase[indice - 1].isalpha() and (indice + 2) < len(phrase) and not phrase[indice + 2].isalpha() :
                phrase = phrase.replace(i+str(phrase[indice+2]),"Mount ")

    # Nettoyage de la phrase

    i = 0
    while (i < 3):
        phrase = cleanTitle(phrase,i)
        phrase = phrase.replace("  ", " ")
        i+=1

    if len(phrase) > 0 and not phrase[-1].isalpha():
         phrase = phrase[:-1]
    phrase = phrase.strip()
    print("After clean :",phrase)

    #ciblage du code d'Etat(WA,CO,KY etc...) avec une expression reguliere

    motif = re.compile(r"[A-Z]{2}\W{1}")
    code = motif.findall(phrase)
    if len(code) > 0 :
        code = list(map(lambda x: x[:-1],code))
        print("code pays trouvé :",code)
    else:
        code = 0

    # Filtrage des mots utiles, nom propre, adjectif
    print("\n Filtrage les mots utiles, nom propre, adjectif \n")

    resultat = nltk.ne_chunk(nltk.pos_tag(nltk.word_tokenize(phrase)))
    print("lexical :",resultat.pos())
    loc = findLocation(resultat.pos())
    mots = locateFormed(loc)
    print("Mot utiles pour combinaison :", mots.split(), "\n")

# Creation des combinaisons de mots en fonction d'un pivot (dernier mot de la combinaison) et de la taille de la combinaison
    def formeCombi(taille,pivot):
        global combi
        combi = mots.split()[pivot-(taille-1)]
        for i in range(pivot-(taille-2), pivot+1):
            combi += " "
            combi += mots.split()[i]


    # Combinaison et recherche Geoname
    # si ensemble de mots utiles different de 1 alors on va creer toutes les combinaisons possibles
    if len(mots.split()) != 1:
        tailleCombi = 2
        pivotCombi = tailleCombi-1
        combi = mots.split()[0] + " " + mots.split()[1]
        print("taille :",tailleCombi)
        print("pivot :",pivotCombi)

        # Tant que aucune combinaison retourne un resultat, alors nous continuons a creer des nouveaux
        while geoNamesSearch(combi) == -1 and tailleCombi < len(mots.split()):
            print("La combinaison est :", combi, "\n")
            pivotCombi += 1
            if pivotCombi == len(mots.split()):
                pivotCombi = tailleCombi
                tailleCombi += 1
            print("taille :", tailleCombi)
            print("pivot :", pivotCombi)
            formeCombi(tailleCombi,pivotCombi)

        print("La combinaison est :", combi, "\n")

        # Si les candidats composes de plusieurs mot ne retourne rien, on teste chaque mot, un par un
        cpt = 0
        if geoNamesSearch(combi) == -1:
            print("\nAucun candidat composé de plusieurs mots n'avait de solution, passage au candidat composé d'un seul mot :\n")
            while geoNamesSearch(combi) == -1 and cpt < len(mots.split()):
                combi = mots.split()[cpt]
                print("Le candidat est :",combi)
                cpt += 1
    # Si il n'y a qu'un mot utile
    else:
        combi = mots.split()



print("\nCombinaison final :",combi,"\n")
print("Geoname :", geoNamesSearch(combi))