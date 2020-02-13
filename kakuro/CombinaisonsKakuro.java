package kakuro;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CombinaisonsKakuro extends JFrame implements ItemListener {

    protected boolean doitInclure, doitExclure;
    protected JButton jbValider;
    protected JTextArea jtaZoneSolution;
    protected JLabel jlEtqNbrChiffres, jlEtqResultats, jlEtqInclure,
            jlEtqExclure;
    protected JTextField jtfInclure, jtfExclure;
    protected JComboBox jcbNbrChiffres, jcbResultats;
    protected JCheckBox jchckInclure, jchckExclure;
    protected Box boiteGlobale, boiteHoriz1, boiteHoriz2, boiteHoriz3,
            boiteHoriz4,
            boiteHoriz5;
    //protected JScrollPane jspVitre4;

    /** Crée une nouvelle instance de Combinaisonskakuro */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public CombinaisonsKakuro() {

            int i;
            ArrayList<String> sequences;

            doitInclure = false;
            doitExclure = false;

            setTitle("Les combinaisons d'un Kakuro");
            setBounds(100, 100, 500, 500);
            setLayout(new GridBagLayout());

            //définition des composants avant ajout
            jlEtqNbrChiffres = new JLabel("Nombre de chiffres");
            jlEtqNbrChiffres.setMinimumSize(new Dimension(120,20));
            jlEtqNbrChiffres.setMaximumSize(new Dimension(120,20));

            jlEtqResultats = new JLabel("Somme");
            jlEtqResultats.setMinimumSize(new Dimension(60,20));
            jlEtqResultats.setMaximumSize(new Dimension(60,20));

            jlEtqInclure = new JLabel("Inclure les groupes suivants");
            jlEtqInclure.setMinimumSize(new Dimension(180,20));
            jlEtqInclure.setMaximumSize(new Dimension(180,20));

            jlEtqExclure = new JLabel("Exclure les groupes suivants");
            jlEtqExclure.setMinimumSize(new Dimension(180,20));
            jlEtqExclure.setMaximumSize(new Dimension(180,20));

            jtfInclure = new JTextField("", 30);
            jtfInclure.setMinimumSize(new Dimension(100,20));
            jtfInclure.setMaximumSize(new Dimension(100,20));

            jtfExclure = new JTextField("", 30);
            jtfExclure.setMinimumSize(new Dimension(100,20));
            jtfExclure.setMaximumSize(new Dimension(100,20));

            jchckInclure = new JCheckBox();

            jchckExclure = new JCheckBox();

            jtaZoneSolution = new JTextArea(20, 20);
            jtaZoneSolution.setMinimumSize(new Dimension(400,300));
            jtaZoneSolution.setMaximumSize(new Dimension(400,300));
            jtaZoneSolution.setPreferredSize(new Dimension(400,300));

            jcbNbrChiffres = new JComboBox(new Integer[] {
                                           2, new Integer(3),
                                           new Integer(4), new Integer(5),
                                           new Integer(6), new Integer(7),
                                           new Integer(8), new Integer(9)
            });
            jcbNbrChiffres.setMinimumSize(new Dimension(40,20));
            jcbNbrChiffres.setMaximumSize(new Dimension(40,20));

            jcbResultats = new JComboBox(new Integer[] {
                                         3, new Integer(4),
                                         new Integer(5), new Integer(6),
                                         new Integer(7), new Integer(8),
                                         new Integer(9), new Integer(10),
                                         new Integer(11), new Integer(12),
                                         new Integer(13), new Integer(14),
                                         new Integer(15), new Integer(16),
                                         new Integer(17)
            });
            jcbResultats.setMinimumSize(new Dimension(40,20));
            jcbResultats.setMaximumSize(new Dimension(40,20));

            jbValider = new JButton("Valider");
            jbValider.setMinimumSize(new Dimension(120,40));
            jbValider.setMaximumSize(new Dimension(120,40));

            //ajout des ecouteurs et des comportements
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);

            jcbNbrChiffres.addActionListener(new TraitementJCBNbrChiffres(this));

            jcbResultats.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ae) {

                    int i;
                    int nbrChiffres = jcbNbrChiffres.getSelectedIndex() + 2;
                    int resultat = CombinaisonsKakuro.sommeMin(nbrChiffres) +
                                   jcbResultats.getSelectedIndex();

                    String inclusions, exclusions;

                    ArrayList<String> sequences = new ArrayList<String>();
                    inclusions = new String();
                    exclusions = new String();

                    if (doitInclure) {
                        inclusions = jtfInclure.getText();
                    } else {
                        inclusions = "";
                    }
                    if (doitExclure) {
                        exclusions = jtfExclure.getText();
                    } else {
                        exclusions = "";
                    }
                    sequences = CombinaisonsKakuro.combinaisons(resultat,
                            nbrChiffres,
                            inclusions, exclusions);

                    jtaZoneSolution.setText(""); ;
                    for (i = 0; i < sequences.size(); i++) {
                        jtaZoneSolution.append(sequences.get(i));
                        jtaZoneSolution.append("\n");
                    }

                }

            });
            jbValider.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int i;
                    int nbrChiffres = jcbNbrChiffres.getSelectedIndex() + 2;
                    int resultat = CombinaisonsKakuro.sommeMin(nbrChiffres) +
                                   jcbResultats.getSelectedIndex();
                    String inclusions, exclusions;

                    ArrayList<String> sequences = new ArrayList<String>();
                    inclusions = new String();
                    exclusions = new String();

                    if (doitInclure) {
                        inclusions = jtfInclure.getText();
                    } else {
                        inclusions = "";
                    }
                    if (doitExclure) {
                        exclusions = jtfExclure.getText();
                    } else {
                        exclusions = "";
                    }
                    sequences = CombinaisonsKakuro.combinaisons(resultat,
                            nbrChiffres,
                            inclusions, exclusions);

                    jtaZoneSolution.setText(""); ;
                    for (i = 0; i < sequences.size(); i++) {
                        jtaZoneSolution.append(sequences.get(i));
                        jtaZoneSolution.append("\n");
                    }

                }
            });
            jchckInclure.addItemListener(this);
            jchckExclure.addItemListener(this);

            //ajout des composants
            boiteHoriz1 = Box.createHorizontalBox();
            boiteHoriz1.add(jlEtqNbrChiffres);
            boiteHoriz1.add(Box.createHorizontalStrut(10));
            boiteHoriz1.add(jcbNbrChiffres);
            boiteHoriz1.add(Box.createHorizontalStrut(30));
            boiteHoriz1.add(jlEtqResultats);
            boiteHoriz1.add(Box.createHorizontalStrut(10));
            boiteHoriz1.add(jcbResultats);
            boiteHoriz1.setAlignmentX(Box.CENTER_ALIGNMENT);

            boiteHoriz2 = Box.createHorizontalBox();
            boiteHoriz2.add(jchckInclure);
            boiteHoriz2.add(Box.createHorizontalStrut(10));
            boiteHoriz2.add(jlEtqInclure);
            boiteHoriz2.add(Box.createHorizontalStrut(10));
            boiteHoriz2.add(jtfInclure);
            boiteHoriz2.setAlignmentX(Box.CENTER_ALIGNMENT);

            boiteHoriz3 = Box.createHorizontalBox();
            boiteHoriz3.add(jchckExclure);
            boiteHoriz3.add(Box.createHorizontalStrut(10));
            boiteHoriz3.add(jlEtqExclure);
            boiteHoriz3.add(Box.createHorizontalStrut(10));
            boiteHoriz3.add(jtfExclure);
            boiteHoriz3.setAlignmentX(Box.CENTER_ALIGNMENT);

            //jspVitre4 = new JScrollPane(jtaZoneSolution);
            boiteHoriz4 = Box.createHorizontalBox();
            boiteHoriz4.add(jtaZoneSolution);
            boiteHoriz4.setAlignmentX(Box.CENTER_ALIGNMENT);

            boiteHoriz5 = Box.createHorizontalBox();
            boiteHoriz5.add(jbValider);
            boiteHoriz5.setAlignmentX(Box.CENTER_ALIGNMENT);

            boiteGlobale = Box.createVerticalBox();
            boiteGlobale.add(boiteHoriz1);
            boiteGlobale.add(Box.createVerticalStrut(10));
            boiteGlobale.add(boiteHoriz2);
            boiteGlobale.add(Box.createVerticalStrut(10));
            boiteGlobale.add(boiteHoriz3);
            boiteGlobale.add(Box.createVerticalStrut(10));
            boiteGlobale.add(boiteHoriz4);
            boiteGlobale.add(Box.createVerticalStrut(10));
            boiteGlobale.add(boiteHoriz5);
            boiteGlobale.setAlignmentY(Box.CENTER_ALIGNMENT);

            setLayout(new FlowLayout());
            add(boiteGlobale);

            /* La valeur par défaut - c.a.d sequence(3,2) {inclure ="", exclure ="}-
              est calculée */
            sequences = CombinaisonsKakuro.combinaisons(3,2,"","");
            jtaZoneSolution.setText(""); ;
            for (i = 0; i < sequences.size(); i++) {
                jtaZoneSolution.append(sequences.get(i));
                jtaZoneSolution.append("\n");
            }


            setVisible(true);
    }

    protected class TraitementJCBNbrChiffres implements ActionListener {

        CombinaisonsKakuro ckFenetre;

        public TraitementJCBNbrChiffres(CombinaisonsKakuro _ckFenetre) {
            ckFenetre = _ckFenetre;
        }

        @SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent ae) {
            int i;
            int iDebut = CombinaisonsKakuro.sommeMin(jcbNbrChiffres.
                    getSelectedIndex() + 2);
            int iFin = CombinaisonsKakuro.sommeMax(jcbNbrChiffres.
                    getSelectedIndex() + 2);
            jcbResultats.removeAllItems();
            for (i = iDebut; i <= iFin; i++) {
                jcbResultats.addItem(i);
            }
            jcbResultats.setSelectedIndex(0);
        }

    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CombinaisonsKakuro appli = new CombinaisonsKakuro();
    }

    public static int sommeMin(int nbrChiffres) {
        return nbrChiffres * (nbrChiffres + 1) / 2;
    }

    public static int sommeMax(int nbrChiffres) {
        return 10 * nbrChiffres - nbrChiffres * (nbrChiffres + 1) / 2;
    }
    /**
     * Retourne une liste des combinaisons possibles, sous la forme "c1+c2+c3...+cn"
     * où c1...cn sont les différents chiffres.
     * @param resultat int
     * @param nbrChiffres int
     * @param inclusion String
     * @param exclusion String
     * @return ArrayList
     */
    public static ArrayList<String> combinaisons(int resultat, int nbrChiffres,
                                                 String inclusion,
                                                 String exclusion) {

        int nbrRes, nbrResApresInclu, nbrResApresExclu;
        ArrayList<String>
                combinaisons = combinaisonsBrutes(resultat, nbrChiffres);
        nbrRes = combinaisons.size();
        if (!inclusion.equals("")) {
            combinaisons = reduireCombinaisonsEnInclusion(combinaisons,
                    inclusion);
            nbrResApresInclu = combinaisons.size();
        }
        else{
            nbrResApresInclu = nbrRes;
        }
        if (!exclusion.equals("")) {
            combinaisons = reduireCombinaisonsEnExclusion(combinaisons,
                    exclusion);
            nbrResApresExclu = combinaisons.size();
        }
        else{
            nbrResApresExclu = nbrResApresInclu;
        }
        combinaisons.add(0,"Sequence(" + resultat + "," + nbrChiffres + ")");
        combinaisons.add(1,"*******************************************************");
        combinaisons.add(2,"Sans recherche avancée : " + nbrRes +
                          " résultat" +
                          ((nbrRes > 1) ? "s" : ""));
        combinaisons.add(3,"Après inclusions (groupe(s) : "+((!inclusion.equals(""))?inclusion:"aucun")+" ) : "+nbrResApresInclu+" résultat"+((nbrResApresInclu>1)?"s":""));
        combinaisons.add(4,"Après exclusions (groupe(s) : "+((!exclusion.equals(""))?exclusion:"aucun")+" ) : "+nbrResApresExclu+" résultat"+((nbrResApresExclu>1)?"s":""));
        combinaisons.add(5,"******************************************************");
        return (combinaisons);
    }

    /**
     * Retourne une liste des combinaisons possibles, sous la forme "c1+c2+c3...+cn"
     * où c1...cn sont les différents chiffres.
     * @param resultat int
     * @param nbrChiffres int
     * @return ArrayList
     */
    public static ArrayList<String> combinaisonsBrutes(int resultat,
            int nbrChiffres) {
        try {
            int i, pointeur, sommeSeqTemp;
            char c;
            String s;
            ArrayList<String> sequences = new ArrayList<String>(0);

            // intialisation de seqTemp
            StringBuilder seqTemp = new StringBuilder("0");
            for (i = 2; i <= nbrChiffres; i++) {
                seqTemp.append("+0");
            }

            /*calcul de la 1ere somme (éventuelle : pour peu que resultat et
                         nombre de chiffres concordent.*/
            completerSequence(seqTemp, resultat, nbrChiffres, 1);

            pointeur = nbrChiffres - 1;

            /*traitement proprement dit*/
            while (true) {
                //calcule la somme de seqTemp
                sommeSeqTemp = 0;
                for (i = 0; i <= 2 * nbrChiffres - 2; i = i + 2) {
                    sommeSeqTemp += seqTemp.charAt(i) - '0';
                }
                /*si somme atteinte ... l'enregistrer et l'avant dernier chiffre
                 sera incrémenté */
                if (sommeSeqTemp == resultat) {
                    sequences.add(seqTemp.toString());
                    pointeur = nbrChiffres - 1;
                }
                /*sinon le pointeur recule afin de pointer le chiffre précédent
                  lors de la prochaine boucle, et s'il est sorti de la
                  séquence ... forcément, le travail est terminé*/
                else {
                    if (pointeur > 1) {
                        pointeur -= 1;
                    } else {
                        break;
                    }
                }
                /*sequence[chiffrePointéParPointeur] += 1 */
                seqTemp.setCharAt(2 * pointeur - 2,
                                  (char) (seqTemp.charAt(2 * pointeur - 2) + 1));
                completerSequence(seqTemp, resultat, nbrChiffres, pointeur + 1);
            }

            return sequences;
        } catch (StringIndexOutOfBoundsException sioobe) {
            sioobe.printStackTrace();
            return null;
        } catch (IndexOutOfBoundsException ioobe) {
            ioobe.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        } ;

        /**
         * Reduits une liste de combinaisons en tenant compte des chiffres à exclure.
         * Attention! Chaque groupe doit se présenter sous la forme {c1,c2,..cn} , où c1 .. cn sont les différents chiffres du groupe.
         * De plus, chaque groupe doit être séparé par un espace, et il ne doit EN AUCUN CAS avoir d'espace avant le premier groupe.
         * La taille des différents groupes importe peu. Enfin, un même groupe ne doit pas être passé en paramètre des
         * deux fonctions de réduction (inclusion et exclusion), toutefois - et c'est fort logique-, un même groupe peut appraître plusieures fois
         * dans la même fonction.
         * @param combinaisons ArrayList
         * @param inclusions String
         * @param nbrChiffres int
         * @return ArrayList
         */
        public static ArrayList<String> reduireCombinaisonsEnInclusion(
                ArrayList<String> combinaisons, String inclusions) {

            int i;
            String[] chiffresGroupeCour;
            int combCour, melangeCour, groupCour, indexCourDansMelangeCour;
            int nbrChiffresGroupeCour;
            boolean tousLesGroupesValides;
            boolean unMelangeInclus;
            String strCombCour, strChiffreCourGroupeCour;
            String[] groupes;
            ArrayList<String[]> melangesAInclure;

            groupes = inclusions.split("\\s+");
            melangesAInclure = produitCartesienExclusif(groupes);

            /* Pour avoir le droit de conserver une combinaison, il suffira que l'un des melanges inclus dans
             melangesAInclure soit contenu.
             Pour qu'un mélange soit inclu, tous ses chiffres doivent être inclus :
             c'est une lapalissade, mais se le dire évite bien des complications*/
            for (combCour = 0; /*rien*/ ; combCour += 1) {

                if (combCour >= combinaisons.size()) {
                    break;
                }
                unMelangeInclus = false;
                for (melangeCour = 0; melangeCour < melangesAInclure.size();
                                   melangeCour += 1) {
                    tousLesGroupesValides = true;
                    for (indexCourDansMelangeCour = 0;
                            indexCourDansMelangeCour <
                            melangesAInclure.get(melangeCour).length;
                            indexCourDansMelangeCour += 1) {
                        strChiffreCourGroupeCour = melangesAInclure.get(
                                melangeCour)[indexCourDansMelangeCour];
                        strCombCour = combinaisons.get(combCour);
                        if (strCombCour.indexOf(strChiffreCourGroupeCour) ==
                            -1) {
                            tousLesGroupesValides = false;
                            break;
                        }
                    }
                    if (tousLesGroupesValides) {
                        unMelangeInclus = true;
                        break;
                    }
                }
                if (!unMelangeInclus) {
                    combinaisons.remove(combCour);
                    combCour -= 1;
                }
            }
            return combinaisons;
        }

        /**
         * Reduits une liste de combinaisons en tenant compte des chiffres à exclure.
         * Attention! Chaque groupe doit se présenter sous la forme {c1,c2,..cn} , où c1 .. cn sont les différents chiffres du groupe.
         * De plus, chaque groupe doit être séparé par un espace, et il ne doit EN AUCUN CAS avoir d'espace avant le premier groupe.
         * La taille des différents groupes importe peu. Enfin, un même groupe ne doit pas être passé en paramètre des
         * deux fonctions de réduction (inclusion et exclusion), toutefois - et c'est fort logique-, un même groupe peut appraître plusieures fois
         * dans la même fonction.
         * @param combinaisons ArrayList
         * @param exclusions String
         * @param nbrChiffres int
         * @return ArrayList
         */

        public static ArrayList<String> reduireCombinaisonsEnExclusion(
                ArrayList<String> combinaisons, String exclusions) {

            int i;
            String[] chiffresGroupeCour;
            int combCour, melangeCour, groupCour, indexCourDansMelangeCour;
            int nbrChiffresGroupeCour;
            boolean tousLesGroupesValides;
            boolean unMelangeInclus;
            String strCombCour, strChiffreCourGroupeCour;
            String[] groupes;
            ArrayList<String[]> melangesAExclure;

            groupes = exclusions.split("\\s+");
            melangesAExclure = produitCartesienExclusif(groupes);

            /* Pour avoir le droit d'exclure une combinaison, il suffira que l'un des
                                 mélanges de melangeAInclure soit inclus.
             Pour qu'un mélange soit inclu, tous ses chiffres doivent être inclus :
             c'est une lapalissade, mais se le dire évite bien des complications*/
            for (combCour = 0; /*rien*/ ; combCour += 1) {

                if (combCour >= combinaisons.size()) {
                    break;
                }
                unMelangeInclus = false;
                for (melangeCour = 0; melangeCour < melangesAExclure.size();
                                   melangeCour += 1) {
                    tousLesGroupesValides = true;
                    for (indexCourDansMelangeCour = 0;
                            indexCourDansMelangeCour <
                            melangesAExclure.get(melangeCour).length;
                            indexCourDansMelangeCour += 1) {
                        strChiffreCourGroupeCour = melangesAExclure.get(
                                melangeCour)[indexCourDansMelangeCour];
                        strCombCour = combinaisons.get(combCour);
                        if (strCombCour.indexOf(strChiffreCourGroupeCour) ==
                            -1) {
                            tousLesGroupesValides = false;
                            break;
                        }
                    }
                    if (tousLesGroupesValides) {
                        unMelangeInclus = true;
                        break;
                    }
                }
                /* Voila ce le traitement qui diffère de reduireEnInclusion :
                 la condition est juste inversée !!!*/
                if (unMelangeInclus) {
                    combinaisons.remove(combCour);
                    combCour -= 1;
                }
            }

            return combinaisons;

        }

        /**
         * Complète une séquence de manière à ce que les chiffres situés à
         * partir de l'index iDebut forment la sequence restante minimale.
         * Les chiffres situés à partir de l'index iDebut spécifié importent
         * peu.
         * Il se peut que la sequence, si la somme ne correspond pas au nombre
         * de chiffres, soit juste complétée avec des chiffres croissants, et
         * que des 9 en queue de sequence.
         *
         * @param chaineTampon StringBuilder - La StringBuilder contenant la
         * séquence à compléter
         * @param resultat int - Le résultat que la séquence doit contenir
         * @param nbrChiffres int - Le nombre de chiffres de la séquence
         * @param iDebut int - Le premier chiffre de la séquence à compléter
         *
         */
        public static void completerSequence(StringBuilder chaineTampon,
                                             int resultat, int nbrChiffres,
                                             int iDebut) {

            int i, chiffreAns, chiffreCur, ri, ni, sommeAnsDebut = 0;
            String s;

            //attribution de la valeur de chiffreAns
            if (iDebut >= 2) {
                chiffreAns = (chaineTampon.charAt(2 * (iDebut - 1) -
                                                  2) - '0');
            } else {
                chiffreAns = 0;
            }

            //calcul des iDebut - 1 premiers chiffres
            for (i = 0; i <= 2 * (iDebut - 1) - 2; i = i + 2) {
                sommeAnsDebut +=
                        (chaineTampon.charAt(i) - '0');
            }

            //ri et ni
            ri = resultat - sommeAnsDebut;
            ni = nbrChiffres - iDebut + 1;

            //complétion proprement dite
            for (i = iDebut; i <= nbrChiffres; i++) {
                /* Attention! Si le chiffre précédent vaut 9, par précaution,
                   compléter le reste de la séquence avec des 9 */
                if ((chiffreAns == 9) && (i <= nbrChiffres)) {
                    s = new String("9");
                } else {
                    s = new String(Integer.toString(Math.max(chiffreAns + 1,
                            ri - sommeMax(ni - 1))));
                }
                chaineTampon.setCharAt(2 * i - 2, s.charAt(0));
                chiffreCur = chaineTampon.charAt(2 * i - 2) - '0';
                ri -= chiffreCur;
                chiffreAns = chiffreCur;
                ni -= 1;

            }

        }

        /**
         * Les groupes doivent etre de la forme {c1,c2,c3...cn} avec c1...cn les
         * différents chiffres. Les groupes peuvent être de tailles différentes.
         * <br><b>Attention!</b> Il se peut qu'un groupe se retrouve plusieures
         * fois avec un agencement différent de ses chiffres. Mais, dans l'état
         * actuel des choses (version 0.0.1), cela ne change en rien
         * l'algorithme de reduction des combinaisons en inclusion ou
         * en exclusion.
         * @param groupes String[] les groupes dont on doit faire le produit
         * cartésien.
         * @return ArrayList : l'ensemble des résultats
         */
        public static ArrayList<String[]> produitCartesienExclusif(String[]
                groupes) {

            boolean fautQuitterProdCour;
            int i, prodCourant, indexCourProdCour, indexGroupeCour;
            String valGroupeCour;
            boolean[] chiffresDejaInclus;
            int[] nbrChiffres;
            String[] produit;
            ArrayList<int[]> produitCartesienDesIndexs;
            ArrayList<String[]> retour;

            try {
                retour = new ArrayList<String[]>(0);
                produit = new String[groupes.length];
                nbrChiffres = new int[groupes.length];
                chiffresDejaInclus = new boolean[9];

                /* Calcul des nombres de chiffres */
                for (i = 0; i < groupes.length; i += 1) {
                    nbrChiffres[i] = (int) ((groupes[i].length() - 2) / 2) + 1;
                    /* (Je retire les 2 accolades, puis la division permet de tenir
                     compte des virgules), et enfin ajouter 1 car le nombre divisé
                     est impair et qu'il y a plus de chiffres que de virgules - un
                                     chiffre a été négligé dans la division )*/
                }
                produitCartesienDesIndexs = comptage(nbrChiffres);
                for (prodCourant = 0;
                                   prodCourant < produitCartesienDesIndexs.size();
                                   prodCourant += 1) {
                    Arrays.fill(chiffresDejaInclus, false);
                    fautQuitterProdCour = false;
                    for (indexCourProdCour = 0;
                                             indexCourProdCour < groupes.length;
                                             indexCourProdCour += 1) {
                        /* indexCourProdCour vaut aussi le groupe courant */
                        indexGroupeCour = 2 *
                                          produitCartesienDesIndexs.get(
                                                  prodCourant)[
                                          indexCourProdCour] + 1;
                        /* Ne pas oublier l'accolade ouvrante ! */
                        valGroupeCour = String.valueOf(groupes[
                                indexCourProdCour].
                                charAt(indexGroupeCour));
                        produit[indexCourProdCour] = valGroupeCour;
                        if (chiffresDejaInclus[Integer.valueOf(valGroupeCour) -
                            1]) {
                            fautQuitterProdCour = true;
                            break;
                        } else {
                            chiffresDejaInclus[Integer.valueOf(valGroupeCour) -
                                    1] = true;
                        }
                    }
                    if (fautQuitterProdCour) {
                        continue;
                    }
                    retour.add(produit.clone());
                    /* <==> attribution par valeur,
                               et non par référence */
                }
                return retour;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                                              "Format d'inclusion incorrect :\n" +
                                              "peut etre avez-vous entre un groupe vierge", null,
                                              JOptionPane.ERROR_MESSAGE);
                return null;
            }

        }

        /**
         * Retourne l'index reel que le poids d'un nombre représente dans un
         * tableau de longueur <u><i>longueur</i></u> <br><br>
         * Exemple :<br>
         *   3 2 1 0  --> poids du nombre decimal equivalent (5326)<br>
         *  [5 3 2 6]<br>
         *   0 1 2 3 --> indexs reels du tableau<br>
         * <br>
         *  Tout simplement !
         * @param poids int : le poids recherché
         * @param longueur int : la longueur du tableau
         * @return int : l'index du poids recherché dans le tableau
         */
        public static int posReellePoids(int poids, int longueur) {
            return longueur - poids - 1;
        }

        /**
         * Compte en respectant les bases de chaque chiffre. Ainsi si le chiffre
         * de poids p a pour base b : alors ce chiffre ne peut aller que de 0 à
         *  b-1.<br>
         * Ainsi, pour compter dans une base b, on passe un tableau bases[] de
         * bases identiques.
         * @param bases int[] : l'ensemble des bases
         * @return ArrayList : l'ensemble des résultats
         */
        public static ArrayList<int[]> comptage(int[] bases) {
            int i, poids, longueur;
            int[] indexs;
            ArrayList<int[]> retour;

            longueur = bases.length; //Optimisation et simplification de code
            indexs = new int[longueur];
            retour = new ArrayList<int[]>(0);

            poids = 0;
            while (true) {
                /* Si le chiffre de poids courant a atteind son maximum */
                if (indexs[posReellePoids(poids, longueur)] >=
                    bases[posReellePoids(poids, longueur)] - 1) {
                    if (poids >= longueur - 1) {
                        break;
                    } else {
                        poids += 1;
                    }
                } else {
                    /*Exploitation du tableau indexs et passage à la valeur
                                           suivante*/
                    retour.add(indexs.clone());

                    indexs[posReellePoids(poids, longueur)] += 1;

                    /* Bien sûr, vider les poids inférieurs */
                    if (poids > 0) {
                        for (i = posReellePoids(poids - 1, longueur);
                                 i <= posReellePoids(0, longueur); i += 1) {
                            indexs[i] = 0;
                        }
                        poids = 0;
                    }

                }
            }
            /* Et la dernière valeur, qui faisait défaut */
            retour.add(indexs.clone());
            return retour;
        }

        public void itemStateChanged(ItemEvent e) {
            int i, nbrChiffres, resultat;
            String inclusions, exclusions;
            ArrayList<String> sequences;

            if (e.getSource() == jchckInclure || e.getSource() == jchckExclure){
                if (e.getSource() == jchckInclure) {
                    doitInclure = (doitInclure) ? false : true;
                }
                if (e.getSource() == jchckExclure) {
                    doitExclure = (doitExclure) ? false : true;
                }
                nbrChiffres = jcbNbrChiffres.getSelectedIndex() + 2;
                resultat = CombinaisonsKakuro.sommeMin(nbrChiffres) +
                            jcbResultats.getSelectedIndex();
                sequences = new ArrayList<String>();
                inclusions = new String();
                exclusions = new String();

                if (doitInclure) {
                        inclusions = jtfInclure.getText();
                    } else {
                        inclusions = "";
                    }
                    if (doitExclure) {
                        exclusions = jtfExclure.getText();
                    } else {
                        exclusions = "";
                    }
                    sequences = CombinaisonsKakuro.combinaisons(resultat,
                            nbrChiffres,
                            inclusions, exclusions);

                    jtaZoneSolution.setText(""); ;
                    for (i = 0; i < sequences.size(); i++) {
                        jtaZoneSolution.append(sequences.get(i));
                        jtaZoneSolution.append("\n");
                    }

            }
        }
    }