package com.sacak.entities;



import com.sacak.databases.LeagueDB;
import com.sacak.models.DatabaseModel;
import com.sacak.utility.FileIOWriter;
import com.sacak.utility.ILoad;
import com.sacak.utility.enums.EDivision;
import com.sacak.utility.enums.ERegion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class League extends BaseEntity implements ILoad {

    private String leaugeName;
    private List<Integer> teamIdList;
    private ERegion region;
    private EDivision division;
    private String season;
    private final LocalDate BEGINNING_OF_SEASON;
    private LocalDate currentDate;


    public League(LeagueDB leagueDB, LocalDate beginningOfSeason){
        super(leagueDB);
        teamIdList = new ArrayList<>();
        BEGINNING_OF_SEASON = beginningOfSeason;
        currentDate = LocalDate.now(); //Todo test aşamsında.
        leagueDB.save(this);
        FileIOWriter.addEntity(this);
    }
    public League(LeagueDB leagueDB,String leaugeName,ERegion region,EDivision division,String season,ArrayList<Integer> teamIdList,LocalDate beginningOfSeasonDate ){
        super(leagueDB);
        currentDate = LocalDate.now();//Todo test aşamsında.
        this.teamIdList = teamIdList;
        this.leaugeName=leaugeName;
        this.region=region;
        this.division=division;
        this.season = season;
        this.BEGINNING_OF_SEASON=beginningOfSeasonDate;


        leagueDB.save(this);
        FileIOWriter.addEntity(this);
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public ERegion getRegion() {
        return region;
    }

    public void setRegion(ERegion region) {
        this.region = region;
    }

    public EDivision getDivision() {
        return division;
    }

    public void setDivision(EDivision division) {
        this.division = division;
    }

    public List<Integer> getTeamIdList() {
        return teamIdList;
    }


    public String getLeaugeName() {
        return leaugeName;
    }

    public void setLeaugeName(String leaugeName) {
        this.leaugeName = leaugeName;
    }

    public LocalDate getBEGINNING_OF_SEASON() {
        return BEGINNING_OF_SEASON;
    }


    @Override
    public String toString() {
        return "League{" +
                "id=" + getId() +
                ", leaugeName='" + getLeaugeName() + '\'' +
                ", region=" + getRegion() +
                ", division=" + getDivision() +
                ", season='" + getSeason() + '\'' +
                ", StartDate=" + getBEGINNING_OF_SEASON().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                '}';
    }

    @Override
    public void saveToDatabase(DatabaseModel databaseModel) {
        databaseModel.leagueDB.findAll().add(this);
    }
}
