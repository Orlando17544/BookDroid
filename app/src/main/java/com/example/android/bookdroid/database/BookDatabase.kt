package com.example.android.bookdroid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [BookIdentifier::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract val bookDatabaseDao: BookDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: BookDatabase? = null

        val artIds = listOf(
            Pair("paulklee0000klee", "9783775705813"),
            Pair("georgbaselitzrec0000base", "3832191321"),
            Pair("caiipliniisecund03plinuoft", "8806566067"),
            Pair("henrymoore00moor", "085331893"),
            Pair("frontiers0000manc", "085331392"),
            Pair("fablesaeso00aesouoft", "0810913135"),
            Pair("cells0000roac", "1556601263"),
            Pair("sesameliliestwol00rusk", "0226033848"),
            Pair("salom00wild", "9780618758920"),
            Pair("maxer00erns", "9781406733174"),
            Pair("dionysiilonginid00long", "1580819532"),
            Pair("cu31924014450716", "9783791311227"),
            Pair("artofwargriff00sunz", "8834317335"),
            Pair("hawthworks01hawtuoft", "9798540447898"),
            Pair("davincicode0000unse_j7b8", "0143037528"),
            Pair("womaninwhite01coll", "9781497317857"),
            Pair("williamblakexvii00blak", "2266198351"),
            Pair("hawthworks09hawtuoft", "171744962"),
            Pair("summatheologica01thomuoft", "9798624112490"),
            Pair("stonesofven01rusk", "9780710064387"),
        )

        val funIds = listOf(
            Pair("magicfarawaytree0000blyt_r9v4", "0603559433"),
            Pair("ameliabedeliagoe00pari", "0688040578"),
            Pair("familiars0000epst", "9781616570972"),
            Pair("myfavoritethings0000reed", "0874495873"),
            Pair("nybc211780", "9780836808810"),
            Pair("gollygumpswallow00cole", "9780688123239"),
            Pair("cinderedna0000jack", "9781842557860"),
            Pair("horridhenrysdrea0000simo", "0439482739"),
            Pair("toocloseforcomfo00maze", "9780142407639"),
            Pair("icoriander0000gard", "0553055275"),
            Pair("guidetofunlearni19walt", "1404827021"),
            Pair("lifeguard0000jone", "076364109"),
            Pair("hellotilly0000dunb", "1406340243"),
            Pair("froggygoestoscho00jona", "9780670867264"),
            Pair("everydaybento50c0000copl", "4805312610"),
            Pair("guidetofunlearniwal00walt", "0553055275"),
            Pair("guidetofunlearni1983walt", "0553055275"),
            Pair("survivors0000huss", "190682391"),
            Pair("doraexplorerscav00linc", "9781906823917"),
            Pair("sweetdreamsclown00cole", "9781412733182"),
            )

        val selfHelpIds = listOf(
            Pair("thinkgrowrichbyn00hill", "0449214923"),
            Pair("isbn_0671752847", "9789628930098"),
            Pair("inquiryintolife0000made_3rded", "9780697360700"),
            Pair("howtoliveon24hou00benn_0", "1602060746"),
            Pair("nosetoilescontra0000gree", "9782092543030"),
            Pair("psychocybernetic0000malt_m3f4", "0143111884"),
            Pair("nakedluh00burr", "0714503916"),
            Pair("musicophiliatale00sack", "1415942668"),
            Pair("powerofpositivet00pealrich", "9780613140980"),
            Pair("48lawsofpow00gree", "9781665182768"),
            Pair("afteryou0000moye_v6l0", "9877390655"),
            Pair("genericanovel0000ferg", "9780143056966"),
            Pair("bookontabooagain00watt", "9781427277879"),
            Pair("yourcollegeexper0004gard", "0534593925"),
            Pair("freedomoflife00calliala", "1595407081"),
            Pair("elsecreto00byrn", "0743571789"),
            Pair("artofseeing00huxl", "070110788"),
            Pair("diversityoflife000wils", "000654746"),
            Pair("littleenginethat00watt", "0916870480"),
            Pair("powerofyoursubc00murp", "0393964698"),
            )

        val biographyIds = listOf(
            Pair("dictionaryofnati03stepuoft", "9780198651024"),
            Pair("margaretogilvy00barrrich", "0353088714"),
            Pair("autobiografiadeu0000yoga", "9781478209522"),
            Pair("plutarchslivesin02plutiala", "0371050286"),
            Pair("corneliinepotisv00nepo", "9780548108208"),
            Pair("xenophonsmemoirs00xenorich", "1404320954"),
            Pair("graceaboundingto00buny", "9780801007293"),
            Pair("confessionsofjea0000unse_q5k5", "9780679413349"),
            Pair("autobiographyofb00fran5", "1536146145"),
            Pair("lifeofsamueljoh03boswuoft", "0192835319"),
            Pair("saureliiaugustin00augu", "9781101116029"),
            Pair("stfrancisofassis00chesuoft", "9798485740924"),
            Pair("cowboy0000murd_y0x0", "9780789467225"),
            Pair("incidentsinlifeo00jacoiala", "9781534716209"),
            Pair("worksofrobertbur01burniala", "9780665288432"),
            Pair("poemswithprefato00keatuoft", "9780674677302"),
            Pair("memoirsofjacques0000casa_m4o7", "9781781394823"),
            Pair("paulvirgini00sain", "9781434618832"),
            Pair("hereticsgilbert00chesuoft", "9798734432815"),
            Pair("analektahellenik00dalz_0", "1426420927"),
            )

        val memoriesIds = listOf(
            Pair("TheGiverFullBook", "2211220592"),
            Pair("larecherchedut02prouuoft", "2253060356"),
            Pair("nineteeneightyfo0000unse_t8z6", "9875800023"),
            Pair("portraitsepia0000alle", "9604102125"),
            Pair("zurpsychopatholofreu", "2228881945"),
            Pair("glassmenageriepl00will_0", "9780451166364"),
            Pair("dinnerathomesick0000tyle_w0b7", "9780785729044"),
            Pair("simpletruth0000bald_y6z0", "2714436390"),
            Pair("URP_4th_edition", "9780789703217"),
            Pair("pleasuresofmemor00rogeiala", "1854770039"),
            Pair("worldofpsycholog0000unse", "0205195717"),
            Pair("oceanatendoflane0000gaim_k7a2", "1663627193"),
            Pair("streganonaoldtal0000depa", "9780671666064"),
            Pair("36hourday00nanc", "9780801826597"),
            Pair("gobetween00hart", "9780812860733"),
            Pair("gregstagebuchjet0000kinn", "9780141360461"),
            Pair("matterandmemory00berguoft", "3787310274"),
            Pair("circlenovel0000egge_u3n7", "0385351399"),
            Pair("laignorancia0000kund", "0060002093"),
            Pair("littlehouse00burt", "9780812428087"),
            )

        val scienceFictionIds = listOf(
            Pair("princessofmars0000burr_k9d8", "1466493283"),
            Pair("in.ernet.dli.2015.209264", "9798451770344"),
            Pair("cihm_03759", "1973549530"),
            Pair("martianchronicle0000brad_v7y9", "9789994289738"),
            Pair("indaysofcomet00wellrich", "9798664038842"),
            Pair("sleeperawakes00welluoft", "1785435353"),
            Pair("foodofgodshowitc00well_0", "1547064986"),
            Pair("firstmeninmoo00well", "9780735105959"),
            Pair("womandestroyed0000beau", "9781619824737"),
            Pair("cu31924012243048", "0824014111"),
            Pair("journeytocentreo00vern_3", "9780451524508"),
            Pair("hitchhikersguide0000doug", "1570421269"),
            Pair("ironh00lond", "9781434615459"),
            Pair("bravenewworldnov0000huxl", "9787536671805"),
            Pair("ghostseer01schiuoft", "1721713662"),
            Pair("lempereurdieuded0000herb_m7x3", "1439501661"),
            Pair("islandofdoctormo00welluoft", "0460872583"),
            Pair("timemachineinven00well", "0883012197"),
            Pair("warofworlds1898well", "9780671471132"),
            Pair("lastman01shell", "9798475563236"),
            )

        val fantasyIds = listOf(
            Pair("wellatworldsendt0002morr", "9780330238458"),
            Pair("phantastesfaerie00macd", "9781600964718"),
            Pair("lilithromance00macd", "1587159740"),
            Pair("crimsonfairybook00lang", "9798499574140"),
            Pair("jurgencomedy00caberich", "9780809572151"),
            Pair("houseboatstyx00bangrich", "9781248931462"),
            Pair("emeraldcityofoz00baum", "9798653366529"),
            Pair("storyofamulet00nesb_0", "1102111783"),
            Pair("kinderund00grim3", "9780665586040"),
            Pair("lastbattle00lewi", "9780006740360"),
            Pair("cihm_78967", "9780140319378"),
            Pair("carmilla00lefa", "1502897199"),
            Pair("cihm_78963", "0848807049"),
            Pair("broodofwitchquee00rohm", "9781793976840"),
            Pair("magicworld0000nesb", "9781617200908"),
            Pair("silverchairbook400csle", "9780027587708"),
            Pair("roadtooz00baum", "9798747400474"),
            Pair("peterpaninkensin00barr", "9781695280748"),
            Pair("littleprince0000sain_y5b5", "9789681513139"),
            Pair("princecaspian00lewi", "1561797863"),
            )

        val educationIds = listOf(
            Pair("0435150634", "poemssecondserie00arno"),
            Pair("2040040730", "e1762emileoudel02rous"),
            Pair("9781406733174", "sesameliliestwol00rusk"),
            Pair("9780160471612", "principlesofpoli00ches"),
            Pair("9780929524375", "actspassedatthir00unit"),
            Pair("9780543885982", "boezioseuerinode00boet"),
            Pair("9781421241340", "lesaventuresde00fn"),
            Pair("9780160458637", "hippolytuscoroni00euri"),
            Pair("1536146145", "nominationhearin011293unit"),
            Pair("9781434610065", "autobiographyofb00fran5"),
            Pair("9355756801", "latemuchadmiredp00shak"),
            Pair("0460871544", "cu31924029523515"),
            Pair("9798540447898", "xenophontoskyrou00xeno"),
            Pair("9789584300577", "cu31924014450716"),
            Pair("9780679642176", "nybc211431"),
            Pair("9782911416132", "hardtimes1854dick"),
            Pair("9782253067207", "essaisintrodpar01montuoft"),
            Pair("9789571344584", "larepubliquedepl01plat"),
            Pair("1420954229", "angelsdemons0000brow_w1a1"),
            Pair("3598716877", "flatland00abbo_475"),
        )

        val fictionIds = listOf(
            Pair("9798422702657", "thehillofdreams00machiala"),
            Pair("2843043085", "carmilla00lefa"),
            Pair("9781691805501", "pathofking00buch"),
            Pair("1654463604", "powerhouse00buchrich"),
            Pair("9798473945751", "unclesilastaleof00lefauoft"),
            Pair("9798543853788", "witchwood0000buch"),
            Pair("1466493283", "princessofmars0000burr_k9d8"),
            Pair("9798483726548", "krine00rine"),
            Pair("9781795329262", "middletemplemurd00flet_0"),
            Pair("1535523778", "clueoftwistedcan00walluoft"),
            Pair("9798536184608", "indaysofdrakeano00flet"),
            Pair("9798796190852", "freefishers0000buch"),
            Pair("9798507516018", "lemysteredesjonq0000wall"),
            Pair("555163048", "parfymenenmordar0000susk"),
            Pair("9780141033501", "napoleon00chesuoft"),
            Pair("9780809120963", "broodofwitchquee00rohm"),
            Pair("9781793976840", "midwinter00buch_0"),
            Pair("9798750954872", "eightcousinsorau74alco"),
            Pair("9780307122247", "anthem0000rand"),
            Pair("9781565115477", "pollyanna0000port_y7y0"),
        )

        val literatureIds = listOf(
            Pair("remainshistorica52chetuoft", "9798594081468"),
            Pair("eugenicsotherevi0000ches", "1850894035"),
            Pair("tanglewoodtalesf00hawt_1", "9783596124442"),
            Pair("castle01kafk", "9781421838212"),
            Pair("emptyhouseotherg00blacrich", "9798683244422"),
            Pair("streetsevenstars00rine", "1419178679"),
            Pair("princesscurdie00macdiala", "9780788502866"),
            Pair("tracta_xxx_1990_23a_5933", "9798646037542"),
            Pair("martineden1908lond", "9781434618832"),
            Pair("paulvirgini00sain", "9785224048076"),
            Pair("metamorphoseonl00apul", "9798756699531"),
            Pair("housebychurchyar00lefa_0", "9788817152334"),
            Pair("ladivinacommedia02dantuoft", "9780330238458"),
            Pair("wellatworldsendt0002morr", "9798734432815"),
            Pair("hereticsgilbert00chesuoft", "9788475670669"),
            Pair("ElCondeLucanorKeller", "1587159740"),
            Pair("lilithromance00macd", "1435348303"),
            Pair("evilshepherd00oppeuoft", "9780810984097"),
            Pair("taotehking00laozrich", "9780371850435"),
            Pair("talesfromshakspe00inlamb", "9381608504"),
            )

        val financeIds = listOf(
            Pair("cihm_56156", "9780665561566"),
            Pair("thinkgrowrichbyn00hill", "0449214923"),
            Pair("inquiryintonatur02smit_0", "0865970084"),
            Pair("safetymanagement00grim", "0256105766"),
            Pair("cihm_02799", "0659029952"),
            Pair("annoxxviireginel00engl", "9780118009324"),
            Pair("foodsupplymanual00grearich", "0143037528"),
            Pair("artofwargriff00sunz", "0848228103"),
            Pair("poemsbothenglish00miltuoft", "9780665565762"),
            Pair("cihm_54065", "9780471195078"),
            Pair("workingpapersiic0002weyg", "0460007750"),
            Pair("lettershoracewal06walpiala", "9782247004560"),
            Pair("courspolitiquee02goldgoog", "9780471694724"),
            Pair("isbn_9780471671510", "0446677450"),
            Pair("richdadpoordadwh0000kiyo_c5p7", "9780619160500"),
            Pair("helpdesktechnolo0000cour", "9780101360524"),
            Pair("transportgovernm0000grea", "9780073368696"),
            Pair("principlesofcorp00brea", "8423349551"),
            Pair("millnium0000unse", "0321125924"),
            Pair("economics00lips", "0116216859"),
            )

        val investmentIds = listOf(
            Pair("b32219374_0002", "0100221831"),
            Pair("lasciencedubonho00fran_1", "0446677450"),
            Pair("richdadpoordadwh0000kiyo_c5p7", "0330520989"),
            Pair("americanpsycho0000elli", "0273756001"),
            Pair("internationalbus0000dani_x1x3", "0739314645"),
            Pair("irresistibleforc00stee_0", "9781557756817"),
            Pair("worldeconomicoutmay1990unse", "9781134408214"),
            Pair("accumulationofca00luxe", "9780324561265"),
            Pair("investmentsintro0000mayo_i6p0", "0590292978"),
            Pair("chairformymothe000will", "9780030980664"),
            Pair("financialmanagem00brig_2", "9781568959696"),
            Pair("singlesingle0000unse", "9780446613378"),
            Pair("miyuehoneymoon0000patt", "9781573928915"),
            Pair("memoirsextraord11mackgoog", "9780393062458"),
            Pair("randomwalkdownwa0000malk_n2b5", "0321033876"),
            Pair("internationaleco0010krug", "0415400031"),
            Pair("capitalbudgeting0000bier_u6y4", "9789998416277"),
            Pair("thorndikeencyclo00thor", "9781612680200"),
            Pair("richdadsguidetoi00kiyo_0", "0743420373"),
            Pair("elmillionariodea00thom", "0812929640"),
            )

        val historyIds = listOf(
            Pair("eugenicsotherevi0000ches", "9798594081468"),
            Pair("notesfromundergr0000fyod", "1483706516"),
            Pair("discoveringclass0000chri", "1473888808"),
            Pair("remainshistorica52chetuoft", "1726726894"),
            Pair("menofiron0000pyle_o9u4", "0395909716"),
            Pair("enduringvision00paul_0", "9780867206173"),
            Pair("janesfightingshi0070unse_r2m5", "9780786161065"),
            Pair("historyofdeclinex01gibb", "0141881267"),
            Pair("worksofcharlesdi08dick", "0685349209"),
            Pair("ninetythree00hugorich", "9780674990807"),
            Pair("debellogallico00caesuoft", "9780786159093"),
            Pair("1526thoukydidesm00thucuoft", "9780548108208"),
            Pair("corneliinepotisv00nepo", "0665414447"),
            Pair("democracy01tocq", "9780460000147"),
            Pair("worksofcharlesla01lambuoft", "9781781394823"),
            Pair("memoirsofjacques0000casa_m4o7", "9781404307513"),
            Pair("herodotouhalikar00hero", "9781584777632"),
            Pair("lawsofenglandc01blacuoft", "9787208033986"),
            Pair("studyofhistoryvo0000toyn_p0v7", "5280009318"),
            Pair("quovadisnarrativ00sienuoft", "9780872204904"),
            )

        val computingIds = listOf(
            Pair("safetymanagement00grim", "0256105766"),
            Pair("2001spaceodyssey0000clar_s5y2", "9781568653068"),
            Pair("artemisfowl0000colf_g4l1", "9781439531914"),
            Pair("irobot0000unse_w6s4", "0451035402"),
            Pair("digitalfortress000brow", "9788599296202"),
            Pair("helpdesktechnolo0000cour", "9780619160500"),
            Pair("artemisfowletern00colf", "0141315482"),
            Pair("preynovel0000cric_a9b3", "9780062227201"),
            Pair("internetfordummi00sanm", "073140985"),
            Pair("neuromancer00gibs", "9781118967751"),
            Pair("interactivecompu00kenn", "8758804110"),
            Pair("softwareengineer0003pres", "9780072358575"),
            Pair("ihatecomputers0000hunt", "1260548007"),
            Pair("millnium0000unse", "9780199186686"),
            Pair("masteringtodayss0000mart", "8423349551"),
            Pair("stevejobs0000isaa_h3q5", "9780030247880"),
            Pair("introducingcompu0000blis_k1y2", "6047703739"),
            Pair("sumofallfears00clanrich", "9780471552581"),
            Pair("URP_4th_edition", "9782738206619"),
            Pair("sounder00will", "9780789703217"),
            )

        val technologyIds = listOf(
            Pair("b32218977", "0102237948"),
            Pair("sleeperawakes00welluoft", "1785435353"),
            Pair("artofwargriff00sunz", "0143037528"),
            Pair("lp_the-diary-of-a-young-girl_anne-frank-elinor-basescu", "9780330341882"),
            Pair("b32219374_0002", "0100221831"),
            Pair("leonardo0000leon", "9780834300064"),
            Pair("jurassicparknove0000cric", "2724271645"),
            Pair("managementinform03laud", "9780132304610"),
            Pair("lostworldnovel0000cric_e6o9", "0712676902"),
            Pair("digitalfortress000brow", "9788599296202"),
            Pair("b32218631", "0104077964"),
            Pair("childcrafthoww08chic", "9780716660019"),
            Pair("timeline0000unse_d4k2", "061333633"),
            Pair("preynovel0000cric_a9b3", "0345468260"),
            Pair("internetfordummi00sanm", "9780062227201"),
            Pair("stateoffearnovel0000cric_s2q4", "073140985"),
            Pair("artemisfowl0000colf_y2q5", "9781118967751"),
            Pair("onedimensionalm00marc", "8467216891"),
            Pair("sitzungsbericht100stuoft", "9780141353463"),
            Pair("understandingmed00mclu", "9780349122847"),
            )

        val childrenBooksIds = listOf(
            Pair("cihm_78967", "9780140319378"),
            Pair("cu31924013554187", "0856921920"),
            Pair("fantasticmrfox0000dahl_p7c2", "0756982863"),
            Pair("guesshowmuchilov0000mcbr_d8g1", "3737360804"),
            Pair("jamesgiantpeach00roal", "9782070513741"),
            Pair("uglyduckling0000ande", "9780216893443"),
            Pair("peterrabb00pott", "9780723249023"),
            Pair("veryhungrycaterp00carl", "9783806742312"),
            Pair("fablesaeso00aesouoft", "0226033848"),
            Pair("railwaychildren0000nesb_f0n9", "9780571331512"),
            Pair("heidi00spyr", "0517614502"),
            Pair("fivechildrenitph0000unse", "9798479373961"),
            Pair("lescontesenverse00perruoft", "9782010020056"),
            Pair("taleofjemimapudd00pott_6", "9780721431970"),
            Pair("littleprince0000sain_y5b5", "9789681513139"),
            Pair("isbn_9781453053102", "1789550610"),
            Pair("charliechocolate0000roal_m9q2", "9781405661560"),
            Pair("peterwendy00barr2", "0786117869"),
            Pair("cihm_90364", "1721165142"),
            Pair("junglebook00kipl_3", "9798830410052"),
            )

        val mysteryIds = listOf(
            Pair("widowlerougenove00gabo", "9798526944380"),
            Pair("fiftycandles0000bigg", "9798801863542"),
            Pair("angelofterror00walluoft", "9798575224211"),
            Pair("housewithoutkey00bigg", "9798631387737"),
            Pair("deadmensmoney00fletgoog", "1790897548"),
            Pair("middleofthings00fletuoft", "1690701919"),
            Pair("catseye0000raus", "169374645"),
            Pair("cihm_66449", "9798787391473"),
            Pair("manincorner00orcziala", "9798777614773"),
            Pair("rholmescobeingre00banguoft", "0486145700"),
            Pair("lemysteredesjonq0000wall", "1428089918"),
            Pair("mysteryoforcival0000gabo", "9798507516018"),
            Pair("lhommeaucompletm0000chri_l9k6", "9798472241670"),
            Pair("bat00rine", "9781661403416"),
            Pair("afterhouse0000rine", "9798473681703"),
            Pair("techniquemyster00wellgoog", "1594565007"),
            Pair("casejenniebrice00rinegoog", "9798474141985"),
            Pair("maninlowerten00rineiala", "9780821721933"),
            Pair("450zpaddington0000chri_v5b0", "1592249248"),
            Pair("TheSignOfTheFour", "9780061738449"),
            )

        val suspenseIds = listOf(
            Pair("clueoftwistedcan00walluoft", "1535523778"),
            Pair("fiftycandles0000bigg", "9798801863542"),
            Pair("angelofterror00walluoft", "9798575224211"),
            Pair("parfymenenmordar0000susk", "555163048"),
            Pair("unclesilastaleof00lefauoft", "9780141033501"),
            Pair("krine00rine", "9798473945751"),
            Pair("eyeofosirisdetec00free", "9798483726548"),
            Pair("afterhouse0000rine", "9798763634716"),
            Pair("cu31924012926055", "1594565007"),
            Pair("princessofmars0000burr_k9d8", "9798515999308"),
            Pair("broodofwitchquee00rohm", "1466493283"),
            Pair("angelsdemons0000brow_w1a1", "9781793976840"),
            Pair("davincicode0000unse_j7b8", "9789571344584"),
            Pair("iwillrepayaroma00orczgoog", "2266198351"),
            Pair("thirtyninesteps00buch_2", "9798503793437"),
            Pair("mysteryofyellowr0000lero_q2k7", "9798522620462"),
            Pair("nameofrose000ecou", "9782013220460"),
            Pair("deathonnile01chri", "9780330284141"),
            Pair("dli.ernet.470124", "0062302671"),
            Pair("pearl00stei_3", "9780701112585"),
            )

        val romanceNovelIds = listOf(
            Pair("krine00rine", "9798483726548"),
            Pair("streetsevenstars00rine", "9798683244422"),
            Pair("longliveking00rine", "9798461754150"),
            Pair("lodoreshelley01shelrich", "167886658"),
            Pair("lilithromance00macd", "9798504538327"),
            Pair("dangerousdays00rinerich", "1587159740"),
            Pair("comusmaskadapted00miltuoft", "9798728648727"),
            Pair("ageoffableorbeau00bulfiala", "9781595470652"),
            Pair("heptameron0003marg", "0679640010"),
            Pair("freefishers0000buch", "9782253060482"),
            Pair("completeworksoft01gautuoft", "9798796190852"),
            Pair("iwillrepayaroma00orczgoog", "9781420948622"),
            Pair("maranovelaame00isaa", "9798503793437"),
            Pair("eyeofosirisdetec00free", "1933499230"),
            Pair("cu31924026675219", "9798763634716"),
            Pair("memoriasPostumasBrasCubas", "3815419328"),
            Pair("ladivinacommbass03dant", "1521559708"),
            Pair("laprincessedec00lafa", "8842500631"),
            Pair("poemadelcid01voll", "0811210707"),
            Pair("mortedarthursirt00malo", "9780452004368"),
            )

        val religionIds = listOf(
            Pair("stfrancisofassis00chesuoft", "9798485740924"),
            Pair("orthodoxy00chesuoft", "1557424012"),
            Pair("delsentimientotr00unam_0", "9781153738453"),
            Pair("cu31924012243048", "0824014111"),
            Pair("christianaerelig00calv", "9780804204897"),
            Pair("saureliiaugustin00augu", "9781101116029"),
            Pair("hereticsgilbert00chesuoft", "9798734432815"),
            Pair("cu31924013462837", "9798463152411"),
            Pair("boezioseuerinode00boet", "9780929524375"),
            Pair("ExercitiaSPIgnatiiDeLoyola", "9780912422862"),
            Pair("huberthowworksof19bancrich", "0665141599"),
            Pair("quovadisnarrativ00sienuoft", "5280009318"),
            Pair("peoplesmarxabrid00marxuoft", "0043310184"),
            Pair("thoughtsonreligix00pasc", "9780872207172"),
            Pair("cu31924014323772", "1582343802"),
            Pair("rainbow0000unse_p8v6", "9781975902087"),
            Pair("summatheologica01thomuoft", "9780521394031"),
            Pair("leguidedesegares03maim", "9789682940415"),
            Pair("bub_gb_x-7OAAAAMAAJ", "8382652387"),
            Pair("manwhowasthursda00ches", "9798716928619"),
            )

        val spiritualityIds = listOf(
            Pair("PracticalMysticism", "1374872075"),
            Pair("ExercitiaSPIgnatiiDeLoyola", "9780912422862"),
            Pair("holywarmadebysha05buny", "9781846857836"),
            Pair("autobiografiadeu0000yoga", "9781478209522"),
            Pair("flatland00abbo_475", "1420954229"),
            Pair("stfrancisofassis00chesuoft", "9798485740924"),
            Pair("graceaboundingto00buny", "9780801007293"),
            Pair("thealchemist_201908", "9789504915164"),
            Pair("saureliiaugustin00augu", "9781101116029"),
            Pair("orthodoxy00chesuoft", "1557424012"),
            Pair("cu31924014323772", "1582343802"),
            Pair("bub_gb_x-7OAAAAMAAJ", "8382652387"),
            Pair("poemsdon01donnuoft", "0312114680"),
            Pair("holybible0000unse_d3u7", "0785260714"),
            Pair("pilgrimsprogressjbunyan", "9780353606913"),
            Pair("davincicode0000unse_j7b8", "2266198351"),
            Pair("fromdeathcamptoe0000fran", "8690108548"),
            Pair("TheFollowingOfChrist1838", "1412113512"),
            Pair("kybalionstudyofh0000thre_u3b3", "9780943217185"),
            Pair("taoteching0000laoz", "9780394480848"),
            )

        val healthIds = listOf(
            Pair("nominationhearin011293unit", "9780160458637"),
            Pair("foodsupplymanual00grearich", "9780118009324"),
            Pair("goldenboughstudy01fraz", "9780333521984"),
            Pair("domesticmedicine00buchuoft", "082405931"),
            Pair("b20415588_001", "9780824059316"),
            Pair("flowersinattic0000andr", "0946495033"),
            Pair("b31483860", "9783442411429"),
            Pair("mindthatfoundits1908beer", "1697874398"),
            Pair("anatomyofmelanch02burt", "0404078222"),
            Pair("fitwellcoreconce02edfahe", "9781259751257"),
            Pair("contemporarynutr0000ward_h5m6", "9781259706608"),
            Pair("advancesincancer0000unse_k7u3", "9780080522296"),
            Pair("b31486472", "0857521136"),
            Pair("wonder0000pala_i4b7", "9780341712794"),
            Pair("carefeedingof00holt", "3882260920"),
            Pair("woyzeckbuch00buch", "0470170174"),
            Pair("nutritionscience0002smol", "1854772295"),
            Pair("pleasuresofimagi00aken", "0134385454"),
            Pair("isbn_9780321602473", "0205195717"),
            Pair("worldofpsycholog0000unse", "9780080562131"),
            )

        val lifeStyleIds = listOf(
            Pair("candide0000unse_p9u7", "9781975949433"),
            Pair("secretgarden00burn", "9780397321650"),
            Pair("keepaspidistrafl00orwe", "9780140016987"),
            Pair("seastar00marg", "0001382012"),
            Pair("longwayfromchica00peck_0", "9780307207395"),
            Pair("bluewillow00dori", "9780808538165"),
            Pair("oxcartman00hall", "1591127963"),
            Pair("harlembeat130013nish", "9781931514002"),
            Pair("lechatondisparu0000amer_o4x6", "9780746048054"),
            Pair("winterroom00paul_0", "9788804491590"),
            Pair("goggleskeat00keat", "9788700382817"),
            Pair("dontsweatsmallst0000carl_e8d9", "0786866268"),
            Pair("promotinghealthy0000upto", "1447921364"),
            Pair("cityhomes0000smit", "9781406263213"),
            Pair("rushesnovel00rech", "9780802134974"),
            Pair("sociologyofhealt0000unse_n8u2", "0415116465"),
            Pair("isbn_0316701521", "0606304886"),
            Pair("bodybookfeedmove0000diaz", "9780062252753"),
            Pair("consumersimperiu0000hoga", "0807830895"),
            Pair("howtohavelifesty00cris", "9780900821349"),
            )

        val categoriesIds = listOf(
            Pair(artIds, "Art"),
            Pair(funIds, "Fun"),
            Pair(selfHelpIds, "Self help"),
            Pair(biographyIds, "Biography"),
            Pair(memoriesIds, "Memories"),
            Pair(scienceFictionIds, "Science fiction"),
            Pair(fantasyIds, "Fantasy"),
            Pair(educationIds, "Education"),
            Pair(fictionIds, "Fiction"),
            Pair(literatureIds, "Literature"),
            Pair(financeIds, "Finance"),
            Pair(investmentIds, "Investment"),
            Pair(historyIds, "History"),
            Pair(computingIds, "Computing"),
            Pair(technologyIds, "Technology"),
            Pair(childrenBooksIds, "Children's books"),
            Pair(mysteryIds, "Mystery"),
            Pair(suspenseIds, "Suspense"),
            Pair(romanceNovelIds, "Romance novel"),
            Pair(religionIds, "Religion"),
            Pair(spiritualityIds, "Spirituality"),
            Pair(healthIds, "Health"),
            Pair(lifeStyleIds, "Lifestyle")
        )

        fun getInstance(context: Context): BookDatabase {
            val sRoomDatabaseCallback: Callback = object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    INSTANCE?.let {
                        //Insert
                        for (categoryIds in categoriesIds) {
                            for (categoryId in categoryIds.first) {
                                val bookIdentifier = BookIdentifier(categoryId.first, categoryId.second, categoryIds.second);
                                it.bookDatabaseDao.insertBookIdentifier(bookIdentifier);
                            }
                        }
                    }
                }
            }

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        "book_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(sRoomDatabaseCallback)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}