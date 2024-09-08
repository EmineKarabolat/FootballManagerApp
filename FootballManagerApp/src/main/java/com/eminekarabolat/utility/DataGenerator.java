package com.eminekarabolat.utility;


import com.eminekarabolat.databases.*;
import com.eminekarabolat.entities.*;
import com.eminekarabolat.models.*;
import com.eminekarabolat.utility.enums.*;

import java.time.LocalDate;
import java.util.*;

public class DataGenerator {

    public static void generateData(DatabaseModel databaseModel){
        generateRandomPlayers(databaseModel.playerDB);
        generateTeams(databaseModel.teamDB);
        generateLeagues(databaseModel.leagueDB);
        generateManagers(databaseModel.managerDB);
        generateStadiums(databaseModel.stadiumDB);
        generateStats(databaseModel.statsDB);
        generateMatchesAndFixture(databaseModel, databaseModel.leagueDB.findByID(1).get()); //todo lig için geçici çözüm
    }


    private static final String[] FIRST_NAMES = {
            "Ahmet", "Mehmet", "Ali", "Mustafa", "Hasan", "Huseyin", "Ibrahim",
            "Kemal", "Yasin", "Cem", "Murat", "Osman", "Ferhat", "Burak", "Emre",
            "Omer", "Halil", "Serkan", "Tuncay", "Levent", "Ersin", "Onur", "Baris",
            "Volkan", "Kadir", "Erdem", "Furkan", "Can", "Ugur", "Salih", "Sinan",
            "Gökhan", "Hakan", "Yavuz", "Erhan", "Rıza", "Tayfun", "Metin", "Dogan",
            "Koray", "Yusuf", "Oguz", "Tolga", "Alper", "Mevlut", "Sahin", "Zafer"
    };

    private static final String[] LAST_NAMES = {
            "Yilmaz", "Kaya", "Demir", "Celik", "Sahin", "Ozturk", "Aydin", "Kilic",
            "Arslan", "Dogan", "Kara", "Koc", "Ozdemir", "Bal", "Simsek", "Ekinci",
            "Polat", "Cetin", "Keskin", "Yucel", "Avci", "Kurt", "Ates", "Bulut",
            "Gunes", "Bozkurt", "Ay", "Turkmen", "Cakir", "Karaca", "Kocak", "Erol",
            "Tan", "Deniz", "Gurbuz", "Soylu", "Unal", "Elmas", "Guler", "Aksoy",
            "Tuna", "Yildiz", "Gul", "Aslan", "Sener", "Ozkan", "Erdogan", "Sezer"
    };

    public static void generateRandomPlayers(PlayerDB playerDB) {
        Random random = new Random();
        int teamId = 1;
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 11; j++) {
                Player player = new Player(playerDB);
                switch (j) {
                    case 0:
                        player.setPosition(EPosition.GOALKEEPER);
                        break;
                    case 1, 2, 3, 4:
                        player.setPosition(EPosition.DEFENCE);
                        break;
                    case 5, 6, 7, 8:
                        player.setPosition(EPosition.MIDFIELDER);
                        break;
                    case 9, 10:
                        player.setPosition(EPosition.FORWARD);
                }
                player.setName(FIRST_NAMES[random.nextInt(0, FIRST_NAMES.length)] + " " + LAST_NAMES[random.nextInt(0, LAST_NAMES.length)]);
                player.setValue(random.nextLong(500_000, 45_000_000));
                player.setSkillLevel(random.nextInt(25, 101));
                player.setAge(String.valueOf(random.nextInt(17, 37)));
                player.setTeamId(teamId);

            }
            teamId++;
        }
    }

    public static void generateStats(StatsDB statsDB){

        for(int i =1;i<=20;i++){
           new Stats(statsDB,1,i);
        }
    }

    public static void generateTeams(TeamDB teamDB) {

        Team team1 = new Team(teamDB, "Fenerbahce", 252_400_000L);
        Team team2 = new Team(teamDB, "Galatasaray", 210_500_000L);
        Team team3 = new Team(teamDB, "Besiktas", 142_530_000L);
        Team team4 = new Team(teamDB, "Trabzonspor", 98_630_000L);
        Team team5 = new Team(teamDB, "Kocaelispor", 45_000_000L);
        Team team6 = new Team(teamDB, "Caykur Rizespor", 32_750_000L);
        Team team7 = new Team(teamDB, "Samsunspor", 31_700_000L);
        Team team8 = new Team(teamDB, "Antalyaspor", 23_680_000L);
        Team team9 = new Team(teamDB, "Goztepe", 15_330_000L);
        Team team10 = new Team(teamDB, "Adana Demirspor", 23_400_000L);
        Team team11 = new Team(teamDB, "Alanyaspor", 22_460_000L);
        Team team12 = new Team(teamDB, "Sivasspor", 20_780_000L);
        Team team13 = new Team(teamDB, "Kasimpasa", 20_450_000L);
        Team team14 = new Team(teamDB, "Konyaspor", 20_360_000L);
        Team team15 = new Team(teamDB, "Gaziantepspor", 18_180_000L);
        Team team16 = new Team(teamDB, "Hatayspor", 17_980_000L);
        Team team17 = new Team(teamDB, "Bursaspor", 15_300_000L);
        Team team18 = new Team(teamDB, "Sakaryaspor", 12_750_000L);
        Team team19 = new Team(teamDB, "Sipay Bodrum FK", 20_710_000L);
        Team team20 = new Team(teamDB, "BAY", 0L);
    }

    public static void generateStadiums(StadiumDB stadiumDB) {
        new Stadium(stadiumDB, "Ulker Stadyumu Fenerbahce Sukru Saracoglu Spor Kompleksi", "Istanbul", 50609);
        new Stadium(stadiumDB, "Toki Arena", "Istanbul", 52500);
        new Stadium(stadiumDB, "Vodafone Park", "Istanbul", 41758);
        new Stadium(stadiumDB, "Senol Günes Spor Kompleksi", "Trabzon", 40661);
        new Stadium(stadiumDB, "Izmit IsmetPasa Stadyumu", "Kocaeli", 34829);
        new Stadium(stadiumDB, "Caykur Didi Stadyumu", "Rize", 15300);
        new Stadium(stadiumDB, "Samsun Stadyumu", "Samsun", 33000);
        new Stadium(stadiumDB, "Antalya Stadyumu", "Antalya", 33000);
        new Stadium(stadiumDB, "Goztepe Gursel Aksel Stadyumu", "Izmir", 20500);
        new Stadium(stadiumDB, "Adana Stadyumu", "Adana", 33000);
        new Stadium(stadiumDB, "Bahcesehir Okullari Stadyumu", "Antalya", 10500);
        new Stadium(stadiumDB, "Sivas 4 Eylül Stadyumu", "Sivas", 27182);
        new Stadium(stadiumDB, "Kasımpasa Stadyumu", "Istanbul", 14000);
        new Stadium(stadiumDB, "Konya Büyüksehir Stadyumu", "Konya", 42000);
        new Stadium(stadiumDB, "Kalyon Stadyumu", "Gaziantep", 33500);
        new Stadium(stadiumDB, "Hatay Stadyumu", "Hatay", 25000);
        new Stadium(stadiumDB, "Timsah Arena", "Bursa", 32325);
        new Stadium(stadiumDB, "Sakarya Ataturk Stadyumu", "Sakarya", 27569);
        new Stadium(stadiumDB, "Bodrum Belediyesi Bodrumspor Stadyumu", "Mugla", 5000);


    }
    public static void generateLeagues(LeagueDB leagueDB) {
        ArrayList<Integer> trSuperLeagueArrayList =
                new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));

        League turkishSuperLeague =
                new League(leagueDB, "Turkish Super League", ERegion.TURKIYE, EDivision.SUPER_LIG_TR, "2024-2025", trSuperLeagueArrayList, LocalDate.parse("2024-08-09"));
    }

    public static void generateManagers(ManagerDB managerDB) {
        final String password = "123456";
        Manager manager1 = new Manager(managerDB, "Jose Mourinho", "61", "josemourinho", password, 1, 10);
        Manager manager2 = new Manager(managerDB, "Okan Buruk", "50", "okanburuk", password, 2, 3);
        Manager manager3 = new Manager(managerDB, "Giovanni Van Brockhorst", "49", "giovanni", password, 3, 8);
        Manager manager4 = new Manager(managerDB, "Abdullah Avci", "61", "abdullahavci", password, 4, 4);
        Manager manager5 = new Manager(managerDB, "Ertugrul Saglam", "54", "ertugrulsaglam", password, 5, 7);
        Manager manager6 = new Manager(managerDB, "Ilhan Palut", "47", "ilhanpalut", password, 6, 7);
        Manager manager7 = new Manager(managerDB, "Thoman Reis", "46", "thomanreis", password, 7, 7);
        Manager manager8 = new Manager(managerDB, "Alex De Souza", "46", "alexdesouza", password, 8, 8);
        Manager manager9 = new Manager(managerDB, "Stanimir Stoilov", "57", "stanimirstoilov", password, 9, 6);
        Manager manager10 = new Manager(managerDB, "Michael Valkanis", "49", "michaelvalkanis", password, 10, 6);
        Manager manager11 = new Manager(managerDB, "Fatih Tekke", "46", "fatihtekke", password, 11, 7);
        Manager manager12 = new Manager(managerDB, "Bulent Uygun", "53", "bulentuygun", password, 12, 8);
        Manager manager13 = new Manager(managerDB, "Sami Ugurlu", "47", "samiugurlu", password, 13, 6);
        Manager manager14 = new Manager(managerDB, "Aleksandar Stanojevic", "50", "aleksandr", password, 14, 6);
        Manager manager15 = new Manager(managerDB, "Selcuk Inan", "39", "selcukinan", password, 15, 3);
        Manager manager16 = new Manager(managerDB, "Ozhan Pulat", "39", "ozhanpulat", password, 16, 5);
        Manager manager17 = new Manager(managerDB, "Pablo Batalla", "40", "pablobatalla", password, 17, 7);
        Manager manager18 = new Manager(managerDB, "Tuncay Sanli", "42", "tuncaysanli", password, 18, 6);
        Manager manager19 = new Manager(managerDB, "Fikret Öztürk", "40", "fiko", password, 19, 4);
    }




    public static Map<Integer, List<Match>> setDatestoMatches(League league, List<Match> matches) {


        LocalDate matchDate = league.getBEGINNING_OF_SEASON();


        int totalWeek = (league.getTeamIdList().size() - 1) * 2;
        int matchesPerWeek = league.getTeamIdList().size() / 2;
        int matchIndex = 0;

        for (int i = 0; i < totalWeek; i++) {
            for (int j = 0; j < matchesPerWeek; j++) {
                switch (j) {
                    case 0, 1:
                        matches.get(matchIndex++).setMatchDate(matchDate);
                        break;
                    case 2, 3, 4:
                        matches.get(matchIndex++).setMatchDate(matchDate.plusDays(1));
                        break;
                    case 5, 6, 7:
                        matches.get(matchIndex++).setMatchDate(matchDate.plusDays(2));
                        break;
                    case 8, 9:
                        matches.get(matchIndex++).setMatchDate(matchDate.plusDays(3));
                        break;
                }
            }
            matchDate = matchDate.plusDays(7);
        }
        return null;
    }

    public static List<Integer[]> generateFixtureList(int teamCount) {


        List<Integer[]> fixture = new ArrayList<>();

        // Her takımın birbirleriyle iki kez karşılaşacağı fikstürü oluşturuyoruz
        for (int i = 0; i < teamCount - 1; i++) {
            for (int j = 0; j < teamCount / 2; j++) {
                int home = (i + j) % (teamCount - 1);
                int away = (teamCount - 1 - j + i) % (teamCount - 1);
                if (j == 0) {
                    away = teamCount - 1;
                }
                fixture.add(new Integer[]{home, away});
            }
        }

        // İkinci yarı için ters maçları ekliyoruz
        List<Integer[]> reversedFixture = new ArrayList<>();
        for (Integer[] match : fixture) {
            reversedFixture.add(new Integer[]{match[1], match[0]});
        }
        fixture.addAll(reversedFixture);


        //Aynı takım üst üste sürekli ev sahibi olmasın diye
        List<Integer[]> temp = List.copyOf(fixture);

        for(int i =0;i<teamCount-1;i++){
            for(int j=0;j<teamCount/2;j++ ){
                if(i%2==0){
                    fixture.set(j+(i*10),temp.get(190+j+(i*10)));
                    fixture.set(190+j+(i*10), temp.get(j+(i*10)));
                }
            }
        }
        return fixture;
    }
    public static List<Match> setIDToMatches(DatabaseModel databaseModel, List<Integer[]> fixtureList,League league) {
        //maçlara id atar.
        List<Integer> teamIDlist = league.getTeamIdList();
        List<Match> matchesList = new ArrayList<>();

        for (Integer[] matches : fixtureList) {
            Match match = new Match(databaseModel.matchDB);
            if (matches[0] < teamIDlist.size() && matches[1] < teamIDlist.size()) {
                match.setHomeTeamId(teamIDlist.get(matches[0]));
                match.setAwayTeamId(teamIDlist.get(matches[1]));
                matchesList.add(match);
            }
        }
        return matchesList;
    }
        //maçlar için 3 ayrı metod
    public static void generateMatchesAndFixture(DatabaseModel databaseModel,League league){
        int teamNums = league.getTeamIdList().size();
        int matchesPerWeek = teamNums/2;

        List<Integer[]> fixtureListWithID = generateFixtureList(teamNums);

        List<Match> matches = setIDToMatches(databaseModel, fixtureListWithID, league);//Mac nesnesi yarattigimiz yer.

        setDatestoMatches(league, matches);


    }




}