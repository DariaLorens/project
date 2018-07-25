package DBpodcl;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import entity.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;


public class DBProcessor {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/project?useSSL=false";
    public static ArrayList<User> masUser;
    public static ArrayList<TipUser> masTipUser;
    public static ArrayList<TipHaracteristica> masTipHaracteristica;
    public static ArrayList<Status> masStatus;
    public static ArrayList<Sotrudnik> masSotr;
    public static ArrayList<Zakazchik> masZakazchik;
    public static ArrayList<Zadacha> masZadacha;
    public static ArrayList<Podzadacha> masPodzadacha;
    public static ArrayList<Project> masProject;
    public static ArrayList<Doc> masDoc;
    public static ArrayList<Haracteristica> masHaracteristica;
    public static ArrayList<TipDoc> masTipDoc;
    public static ArrayList<Finance> masFinance;
    public static ArrayList<TipFin> masTipFin;
    public static ArrayList<ZavZadSotr> masZavZadSotr;
    public static ArrayList<Integer> masZadNomer;
    public static ArrayList<Integer> masZadId;
    public static ArrayList<Integer> masPodzadNomer;
    public static ArrayList<Integer> masPodzadId;
    public static ArrayList<String> masProjectKod;
    public static int idzadacha;
    public static int idpodzadacha;
    public static int procentVipolnen;
    public static int idharacteristica;
    public int summa;
    public static Date dataPostan;
    private Connection connection;
    private PreparedStatement prepStat;
    private ResultSet resSet;

    public DBProcessor() {
        try {
            DriverManager.registerDriver(new FabricMySQLDriver());
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> findUser() {
        String query = "SELECT user.login, user.pass, user.id_tip FROM project.user;";
        masUser = new ArrayList<User>();
        try {
            prepStat = connection.prepareStatement(query);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masUser.add(new User(resSet.getString("login"), resSet.getString("pass"), resSet.getInt("id_tip")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masUser;
    }

    public int findUser(String login) {
        String query = "SELECT COUNT(*) FROM project.user WHERE login = ?;";
        int count = 0;
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, login);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                count = resSet.getInt(1);
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public ArrayList<User> findUser(String login, String pass) {
        String query = "SELECT user.login, user.pass, user.id_tip FROM project.user " +
                "WHERE user.login = ? AND user.pass = ?";
        masUser = new ArrayList<User>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, login);
            prepStat.setString(2, pass);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masUser.add(new User(resSet.getString("login"),
                        resSet.getString("pass"), resSet.getInt("id_tip")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masUser;
    }

    public ArrayList<Sotrudnik> findSotrudnik() {
        String query = "SELECT sotrudnik.idsotrudnik, sotrudnik.name, sotrudnik.fam, sotrudnik.otch, tip_user.nazvanie AS dolgnost FROM project.tip_user\n" +
                "  LEFT JOIN project.user ON (tip_user.id_tip = user.id_tip) LEFT JOIN project.sotrudnik ON (user.idsotrudnik = sotrudnik.idsotrudnik);";
        masSotr = new ArrayList<Sotrudnik>();
        try {
            prepStat = connection.prepareStatement(query);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masSotr.add(new Sotrudnik(resSet.getInt("idsotrudnik"), resSet.getString("name"),
                        resSet.getString("fam"), resSet.getString("otch"), resSet.getString("dolgnost")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masSotr;
    }

    public ArrayList<Sotrudnik> findSotrudnikPoKod(String kod) {
        String query = "SELECT DISTINCT sotrudnik.idsotrudnik, sotrudnik.name, sotrudnik.fam, sotrudnik.otch, tip_user.nazvanie AS dolgnost FROM project.tip_user\n" +
                "LEFT JOIN project.user ON (tip_user.id_tip = user.id_tip) LEFT JOIN project.sotrudnik ON (user.idsotrudnik = sotrudnik.idsotrudnik)\n" +
                "LEFT JOIN project.podzadacha ON sotrudnik.idsotrudnik = podzadacha.idsotrudnik\n" +
                "LEFT JOIN  project.zadacha ON podzadacha.idzadacha = zadacha.idzadacha\n" +
                "LEFT JOIN project.project ON zadacha.idproject = project.idproject WHERE project.kod = ?;";
        masSotr = new ArrayList<Sotrudnik>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, kod);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masSotr.add(new Sotrudnik(resSet.getInt("idsotrudnik"), resSet.getString("name"),
                        resSet.getString("fam"), resSet.getString("otch"), resSet.getString("dolgnost")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masSotr;
    }

    public ArrayList<Sotrudnik> findSotrudnik(String fam) {
        String query = "SELECT sotrudnik.idsotrudnik, sotrudnik.name, sotrudnik.fam, sotrudnik.otch, tip_user.nazvanie AS dolgnost FROM project.tip_user\n" +
                "  LEFT JOIN project.user ON (tip_user.id_tip = user.id_tip) LEFT JOIN project.sotrudnik ON (user.idsotrudnik = sotrudnik.idsotrudnik)\n" +
                "WHERE sotrudnik.fam = ?;";
        masSotr = new ArrayList<Sotrudnik>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, fam);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masSotr.add(new Sotrudnik(resSet.getInt("idsotrudnik"), resSet.getString("name"),
                        resSet.getString("fam"), resSet.getString("otch"), resSet.getString("dolgnost")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masSotr;
    }

    public int findSotrudnik(String fam, String name, String otch) {
        String query = "SELECT sotrudnik.idsotrudnik FROM project.sotrudnik WHERE fam = ? AND name = ? AND otch = ?;";
        int idsotrudnik = 0;
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, fam);
            prepStat.setString(2, name);
            prepStat.setString(3, otch);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                idsotrudnik = resSet.getInt(1);
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idsotrudnik;
    }

    public ArrayList<Haracteristica> findHaracteristica(String kod) {
        String query = "SELECT project_haracteristica.idproject_haracteristica, haracteristica.nazvanie, project_haracteristica.soderjanie FROM project.project\n" +
                "LEFT JOIN project.project_haracteristica ON project.idproject = project_haracteristica.idproject\n" +
                "LEFT JOIN project.haracteristica ON project_haracteristica.idharacteristica = haracteristica.idharacteristica\n" +
                "WHERE project.kod = ?;";
        masHaracteristica = new ArrayList<Haracteristica>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, kod);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masHaracteristica.add(new Haracteristica(resSet.getInt("idproject_haracteristica"), resSet.getString("nazvanie"),
                        resSet.getString("soderjanie")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masHaracteristica;
    }

    public int findIdProject(String kod) {
        int idproject = 0;
        String query = "SELECT project.idproject FROM project.project WHERE kod = ?;";
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, kod);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                idproject = resSet.getInt(1);
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idproject;
    }

    public int findIdSotrudnik(String login, String pass) {
        int idsotrudnik = 0;
        String query = "SELECT sotrudnik.idsotrudnik FROM project.sotrudnik LEFT JOIN project.user ON sotrudnik.idsotrudnik = user.idsotrudnik\n" +
                "WHERE user.login = ? AND user.pass = ?;";
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, login);
            prepStat.setString(2, pass);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                idsotrudnik = resSet.getInt(1);
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idsotrudnik;
    }

    public ArrayList<Zakazchik> findZakazchik() {
        String query = "SELECT zakazchik.idzakazchik, zakazchik.nazvanie, zakazchik.yr_adres, zakazchik.inn FROM project.zakazchik;";
        masZakazchik = new ArrayList<Zakazchik>();
        try {
            prepStat = connection.prepareStatement(query);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masZakazchik.add(new Zakazchik(resSet.getInt("idzakazchik"), resSet.getString("nazvanie"), resSet.getString("yr_adres"), resSet.getString("inn")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masZakazchik;
    }

    public ArrayList<Zakazchik> findZakazchik(String inn) {
        String query = "SELECT zakazchik.idzakazchik, zakazchik.nazvanie, zakazchik.yr_adres, zakazchik.inn FROM project.zakazchik WHERE zakazchik.inn = ?;";
        masZakazchik = new ArrayList<Zakazchik>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, inn);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masZakazchik.add(new Zakazchik(resSet.getInt("idzakazchik"), resSet.getString("nazvanie"), resSet.getString("yr_adres"), resSet.getString("inn")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masZakazchik;
    }

    public ArrayList<Zadacha> findZadacha(String kod) {
        String query = "SELECT zadacha.idzadacha, zadacha.nomer, zadacha.nazvanie FROM project.project LEFT JOIN project.zadacha ON project.idproject = zadacha.idproject\n" +
                "WHERE project.kod = ? GROUP BY zadacha.nomer;";
        masZadacha = new ArrayList<Zadacha>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, kod);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masZadacha.add(new Zadacha(resSet.getInt("idzadacha"), resSet.getInt("nomer"), resSet.getString("nazvanie")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masZadacha;
    }

    public ArrayList<Zadacha> findZadacha(int idproject, int idsotrudnik) {
        String query = "SELECT zadacha.idzadacha, zadacha.nomer, zadacha.nazvanie\n" +
                "FROM project.project LEFT JOIN project.zadacha ON project.idproject = zadacha.idproject\n" +
                "LEFT JOIN project.podzadacha ON zadacha.idzadacha = podzadacha.idzadacha\n" +
                "LEFT JOIN project.sotrudnik ON podzadacha.idsotrudnik = sotrudnik.idsotrudnik\n" +
                "WHERE project.idproject = ? AND sotrudnik.idsotrudnik = ? AND podzadacha.idstatus_podzadachi=1 " +
                "GROUP BY zadacha.nomer;";
        masZadacha = new ArrayList<Zadacha>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idproject);
            prepStat.setInt(2, idsotrudnik);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masZadacha.add(new Zadacha(resSet.getInt("idzadacha"), resSet.getInt("nomer"),
                        resSet.getString("nazvanie")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masZadacha;
    }

    public Date findDataPostan(int idproject) {
        String query = "SELECT project.data FROM project.project WHERE project.idproject = ?;";
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idproject);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                dataPostan = resSet.getDate("data");
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataPostan;
    }

    public ArrayList<Podzadacha> findPodzadacha(int idzadacha) {
        String query = "SELECT podzadacha.nomer, podzadacha.nazvanie, status_podzadachi.nazvanie AS status, sotrudnik.fam,\n" +
                "sotrudnik.name, sotrudnik.otch, podzadacha.datapostan, podzadacha.datazaver, podzadacha.comment\n" +
                "FROM project.zadacha LEFT JOIN project.podzadacha ON zadacha.idzadacha = podzadacha.idzadacha\n" +
                "LEFT JOIN project.sotrudnik ON podzadacha.idsotrudnik = sotrudnik.idsotrudnik\n" +
                "LEFT JOIN project.status_podzadachi ON podzadacha.idstatus_podzadachi = status_podzadachi.idstatus_podzadachi\n" +
                "WHERE zadacha.idzadacha = ? ORDER BY podzadacha.nomer;";
        masPodzadacha = new ArrayList<Podzadacha>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idzadacha);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masPodzadacha.add(new Podzadacha(resSet.getInt("nomer"), resSet.getString("nazvanie"),
                        resSet.getString("status"), resSet.getString("fam"), resSet.getString("name"),
                        resSet.getString("otch"), resSet.getDate("datapostan"), resSet.getDate("datazaver"),
                        resSet.getString("comment")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masPodzadacha;
    }

    public ArrayList<Podzadacha> findPodzadacha(int idzadacha, int idsotrudnik) {
        String query = "SELECT podzadacha.nomer, podzadacha.nazvanie, status_podzadachi.nazvanie AS status, sotrudnik.fam,\n" +
                "sotrudnik.name, sotrudnik.otch, podzadacha.datapostan, podzadacha.datazaver, podzadacha.comment\n" +
                "FROM project.zadacha LEFT JOIN project.podzadacha ON zadacha.idzadacha = podzadacha.idzadacha\n" +
                "LEFT JOIN project.sotrudnik ON podzadacha.idsotrudnik = sotrudnik.idsotrudnik\n" +
                "LEFT JOIN project.status_podzadachi ON podzadacha.idstatus_podzadachi = status_podzadachi.idstatus_podzadachi\n" +
                "WHERE zadacha.idzadacha = ? AND sotrudnik.idsotrudnik = ? AND podzadacha.idstatus_podzadachi=1 ORDER BY podzadacha.nomer;";
        masPodzadacha = new ArrayList<Podzadacha>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idzadacha);
            prepStat.setInt(2, idsotrudnik);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masPodzadacha.add(new Podzadacha(resSet.getInt("nomer"), resSet.getString("nazvanie"),
                        resSet.getString("status"), resSet.getString("fam"), resSet.getString("name"),
                        resSet.getString("otch"), resSet.getDate("datapostan"), resSet.getDate("datazaver"),
                        resSet.getString("comment")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masPodzadacha;
    }

    public int findIdZadacha(String kod, int nomer) {
        String query = "SELECT zadacha.idzadacha FROM project.zadacha LEFT JOIN project.project ON zadacha.idproject = project.idproject\n" +
                "WHERE project.kod = ? AND zadacha.nomer = ?;";
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, kod);
            prepStat.setInt(2, nomer);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                idzadacha = resSet.getInt(1);
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idzadacha;
    }

    public int findIdZadacha(int id, int nomer) {
        String query = "SELECT zadacha.idzadacha FROM project.zadacha LEFT JOIN project.project ON zadacha.idproject = project.idproject\n" +
                "WHERE project.idproject = ? AND zadacha.nomer = ?;";
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, id);
            prepStat.setInt(2, nomer);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                idzadacha = resSet.getInt(1);
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idzadacha;
    }

    public int findSumma(int idproject, int idtip_fin) {
        String query = "SELECT SUM(finance.summa)\n" +
                "FROM  project.finance LEFT JOIN project.tip_fin ON finance.idtip_fin = tip_fin.idtip_fin\n" +
                "WHERE finance.idproject = ? AND finance.idtip_fin = ?;";
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idproject);
            prepStat.setInt(2, idtip_fin);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                summa = resSet.getInt(1);
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return summa;
    }

    public int findIdPodzadacha(int idzadacha, int nomer) {
        String query = "SELECT podzadacha.idpodzadacha FROM project.podzadacha WHERE podzadacha.idzadacha = ? AND podzadacha.nomer = ?;";
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idzadacha);
            prepStat.setInt(2, nomer);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                idpodzadacha = resSet.getInt(1);
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idpodzadacha;
    }

    public int procentVipolneniya(int idzadacha) {
        String query = "SELECT COUNT(*) AS vsego\n" +
                "FROM project.podzadacha LEFT JOIN project.zadacha ON podzadacha.idzadacha = zadacha.idzadacha\n" +
                "WHERE zadacha.idzadacha = ?;";
        String query2 = "SELECT COUNT(*) AS vipolneno\n" +
                "FROM project.podzadacha LEFT JOIN project.zadacha ON podzadacha.idzadacha = zadacha.idzadacha\n" +
                "WHERE zadacha.idzadacha = ? AND podzadacha.idstatus_podzadachi = 2;";
        double vsego = 0;
        double vipolneno = 0;
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idzadacha);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                vsego = resSet.getInt("vsego");
            }
            prepStat = connection.prepareStatement(query2);
            prepStat.setInt(1, idzadacha);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                vipolneno = resSet.getInt("vipolneno");
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        procentVipolnen = (int) Math.floor((vipolneno / vsego) * 100);
        return procentVipolnen;
    }

    public ArrayList<Project> findProject() {
        String query = "SELECT project.idproject, project.nazvanie, project.kod, project.put, project.ryad, project.stolb, project.polka,\n" +
                "                project.comment, status.nazvanie AS status, zakazchik.nazvanie AS zakazchik, zakazchik.inn\n" +
                "                FROM  project.zakazchik INNER JOIN project.project ON (zakazchik.idzakazchik = project.idzakazchik)\n" +
                "                INNER JOIN project.status ON (project.idstatus = status.idstatus);";
        masProject = new ArrayList<Project>();
        try {
            prepStat = connection.prepareStatement(query);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masProject.add(new Project(resSet.getInt("idproject"),
                        resSet.getString("nazvanie"), resSet.getString("kod"),
                        resSet.getString("put"), resSet.getString("ryad"), resSet.getString("stolb"),
                        resSet.getString("polka"), resSet.getString("comment"),
                        resSet.getString("status"), resSet.getString("zakazchik"), resSet.getString("inn")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masProject;
    }

    public ArrayList<Project> findProject(String kod) {
        String query = "SELECT project.idproject, project.nazvanie, project.kod, project.put, project.ryad, project.stolb, project.polka,\n" +
                "project.comment, status.nazvanie AS status, zakazchik.nazvanie AS zakazchik, zakazchik.inn\n" +
                "FROM  project.zakazchik INNER JOIN project.project ON (zakazchik.idzakazchik = project.idzakazchik)\n" +
                "INNER JOIN project.status ON (project.idstatus = status.idstatus) WHERE project.kod = ?;";
        masProject = new ArrayList<Project>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, kod);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masProject.add(new Project(resSet.getInt("idproject"),
                        resSet.getString("nazvanie"), resSet.getString("kod"),
                        resSet.getString("put"), resSet.getString("ryad"), resSet.getString("stolb"),
                        resSet.getString("polka"), resSet.getString("comment"),
                        resSet.getString("status"), resSet.getString("zakazchik"), resSet.getString("inn")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masProject;
    }

    public ArrayList<String> findProjectHar(int idharacteristica, String soderjanie) {
        String query = "SELECT project.kod \n" +
                "FROM project.haracteristica LEFT JOIN project.project_haracteristica ON haracteristica.idharacteristica = project_haracteristica.idharacteristica\n" +
                "LEFT JOIN project.project ON project_haracteristica.idproject = project.idproject\n" +
                "WHERE haracteristica.idharacteristica = ? AND project_haracteristica.soderjanie = ? ;";
        masProjectKod = new ArrayList<String>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idharacteristica);
            prepStat.setString(2, soderjanie);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masProjectKod.add(new String(resSet.getString("kod")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masProjectKod;
    }

    public ArrayList<Project> findProjectSotr(int idsotrudnik) {
        String query = "SELECT DISTINCT project.idproject, project.nazvanie, project.kod, project.put, project.ryad, project.stolb, project.polka,\n" +
                "project.comment, status.nazvanie AS status, zakazchik.nazvanie AS zakazchik, zakazchik.inn\n" +
                "FROM  project.sotrudnik LEFT JOIN project.podzadacha ON sotrudnik.idsotrudnik = podzadacha.idsotrudnik\n" +
                "LEFT JOIN project.zadacha ON podzadacha.idzadacha = zadacha.idzadacha\n" +
                "LEFT JOIN project.project ON zadacha.idproject = project.idproject\n" +
                "LEFT JOIN project.status ON project.idstatus = status.idstatus\n" +
                "LEFT JOIN project.zakazchik ON project.idzakazchik = zakazchik.idzakazchik\n" +
                "WHERE sotrudnik.idsotrudnik = ?";
        masProject = new ArrayList<Project>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idsotrudnik);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masProject.add(new Project(resSet.getInt("idproject"),
                        resSet.getString("nazvanie"), resSet.getString("kod"),
                        resSet.getString("put"), resSet.getString("ryad"), resSet.getString("stolb"),
                        resSet.getString("polka"), resSet.getString("comment"),
                        resSet.getString("status"), resSet.getString("zakazchik"), resSet.getString("inn")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masProject;
    }

    public ArrayList<Project> findProjectPoStatusu(int idstatus) {
        String query = "SELECT project.idproject, project.nazvanie, project.kod, project.put, project.ryad, project.stolb, project.polka,\n" +
                "project.comment, status.nazvanie AS status, zakazchik.nazvanie AS zakazchik, zakazchik.inn\n" +
                "FROM  project.zakazchik INNER JOIN project.project ON (zakazchik.idzakazchik = project.idzakazchik)\n" +
                "INNER JOIN project.status ON (project.idstatus = status.idstatus) WHERE status.idstatus = ?;";
        masProject = new ArrayList<Project>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idstatus);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masProject.add(new Project(resSet.getInt("idproject"),
                        resSet.getString("nazvanie"), resSet.getString("kod"),
                        resSet.getString("put"), resSet.getString("ryad"), resSet.getString("stolb"),
                        resSet.getString("polka"), resSet.getString("comment"),
                        resSet.getString("status"), resSet.getString("zakazchik"), resSet.getString("inn")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masProject;
    }

    public ArrayList<Project> findProjectPoZakaz(int idzakazchik) {
        String query = "SELECT project.idproject, project.nazvanie, project.kod, project.put, project.ryad, project.stolb, project.polka,\n" +
                "project.comment, status.nazvanie AS status, zakazchik.nazvanie AS zakazchik, zakazchik.inn\n" +
                "FROM  project.zakazchik INNER JOIN project.project ON (zakazchik.idzakazchik = project.idzakazchik)\n" +
                "INNER JOIN project.status ON (project.idstatus = status.idstatus) WHERE zakazchik.idzakazchik = ?;";
        masProject = new ArrayList<Project>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idzakazchik);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masProject.add(new Project(resSet.getInt("idproject"),
                        resSet.getString("nazvanie"), resSet.getString("kod"),
                        resSet.getString("put"), resSet.getString("ryad"), resSet.getString("stolb"),
                        resSet.getString("polka"), resSet.getString("comment"),
                        resSet.getString("status"), resSet.getString("zakazchik"), resSet.getString("inn")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masProject;
    }

    public ArrayList<Project> findProjectPoZakaz(String inn) {
        String query = "SELECT project.idproject, project.nazvanie, project.kod, project.put, project.ryad, project.stolb, project.polka,\n" +
                "project.comment, status.nazvanie AS status, zakazchik.nazvanie AS zakazchik, zakazchik.inn\n" +
                "FROM  project.zakazchik INNER JOIN project.project ON (zakazchik.idzakazchik = project.idzakazchik)\n" +
                "INNER JOIN project.status ON (project.idstatus = status.idstatus) WHERE zakazchik.inn = ?;";
        masProject = new ArrayList<Project>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, inn);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masProject.add(new Project(resSet.getInt("idproject"),
                        resSet.getString("nazvanie"), resSet.getString("kod"),
                        resSet.getString("put"), resSet.getString("ryad"), resSet.getString("stolb"),
                        resSet.getString("polka"), resSet.getString("comment"),
                        resSet.getString("status"), resSet.getString("zakazchik"), resSet.getString("inn")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masProject;
    }

    public ArrayList<Doc> findDoc(String kod, int idtip_doc) {
        String query = "SELECT doc.iddoc, doc.nazvanie, doc.nomer, doc.put, doc.idtip_doc, tip_doc.nazvanie AS tipdoc\n" +
                "FROM  project.project LEFT JOIN project.doc ON (project.idproject = doc.idproject) LEFT JOIN project.tip_doc\n" +
                "ON (doc.idtip_doc = tip_doc.idtip_doc) WHERE project.kod = ? AND doc.idtip_doc = ?;";
        masDoc = new ArrayList<Doc>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, kod);
            prepStat.setInt(2, idtip_doc);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masDoc.add(new Doc(resSet.getInt("iddoc"),
                        resSet.getString("nazvanie"), resSet.getString("nomer"),
                        resSet.getString("put"), resSet.getInt("idtip_doc"),
                        resSet.getString("tipdoc")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masDoc;
    }

    public ArrayList<ZavZadSotr> findZavZadSotr(int idsotrudnik, String datado, String dataposle) {
        String query = "SELECT project.nazvanie AS \"project\", zadacha.nazvanie AS \"zadacha\", podzadacha.nazvanie AS \"podzadacha\", podzadacha.datazaver\n" +
                "FROM project.project LEFT JOIN project.zadacha ON project.idproject = zadacha.idproject\n" +
                "LEFT JOIN project.podzadacha ON zadacha.idzadacha = podzadacha.idzadacha\n" +
                "LEFT JOIN project.sotrudnik ON podzadacha.idsotrudnik = sotrudnik.idsotrudnik\n" +
                "WHERE podzadacha.idsotrudnik = ? AND podzadacha.datazaver BETWEEN ?  AND  ? ORDER BY project.nazvanie;";
        masZavZadSotr = new ArrayList<ZavZadSotr>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idsotrudnik);
            prepStat.setString(2, datado);
            prepStat.setString(3, dataposle);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masZavZadSotr.add(new ZavZadSotr(resSet.getString("project"), resSet.getString("zadacha"),
                        resSet.getString("podzadacha"), resSet.getString("datazaver")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masZavZadSotr;
    }

    public ArrayList<Finance> findFinance(int idproject,int idtip_fin) {
        String query = "SELECT finance.idfinance, finance.date, tip_fin.nazvanie, finance.summa, finance.comment \n" +
                "FROM  project.finance LEFT JOIN project.tip_fin ON finance.idtip_fin = tip_fin.idtip_fin \n" +
                "WHERE finance.idproject = ? AND finance.idtip_fin = ?;";
        masFinance = new ArrayList<Finance>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, idproject);
            prepStat.setInt(2, idtip_fin);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masFinance.add(new Finance(resSet.getInt("idfinance"),
                        resSet.getDate("date"), resSet.getString("nazvanie"),
                        resSet.getBigDecimal("summa"), resSet.getString("comment")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masFinance;
    }

    public ArrayList<TipUser> findAllTipUser() {
        String query = "Select * from project.tip_user";
        masTipUser = new ArrayList<TipUser>();
        try {
            prepStat = connection.prepareStatement(query);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masTipUser.add(new TipUser(resSet.getInt("id_tip"), resSet.getString("nazvanie")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masTipUser;
    }

    public ArrayList<TipDoc> findAllTipDoc() {
        String query = "Select * from project.tip_doc";
        masTipDoc = new ArrayList<TipDoc>();
        try {
            prepStat = connection.prepareStatement(query);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masTipDoc.add(new TipDoc(resSet.getInt("idtip_doc"), resSet.getString("nazvanie")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masTipDoc;
    }

    public ArrayList<TipFin> findAllTipFin() {
        String query = "Select * from project.tip_fin";
        masTipFin = new ArrayList<TipFin>();
        try {
            prepStat = connection.prepareStatement(query);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masTipFin.add(new TipFin(resSet.getInt("idtip_fin"), resSet.getString("nazvanie")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masTipFin;
    }

    public ArrayList<TipHaracteristica> findTipHaracteristica() {
        String query = "Select * from project.haracteristica";
        masTipHaracteristica = new ArrayList<TipHaracteristica>();
        try {
            prepStat = connection.prepareStatement(query);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masTipHaracteristica.add(new TipHaracteristica(resSet.getInt("idharacteristica"), resSet.getString("nazvanie")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masTipHaracteristica;
    }

    public ArrayList<Status> findAllStatus() {
        String query = "Select status.idstatus, status.nazvanie from project.status";
        masStatus = new ArrayList<Status>();
        try {
            prepStat = connection.prepareStatement(query);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masStatus.add(new Status(resSet.getInt("idstatus"), resSet.getString("nazvanie")));
            }
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masStatus;
    }

    public void insertNewUser(String name, String fam, String otch, String login, String pass, int id_tip) {
        int idSotrudnik = 0;
        String query;
        try {
            prepStat = connection.prepareStatement("insert into project.sotrudnik (name, fam, otch) values (?, ?, ?); ");
            prepStat.setString(1, name);
            prepStat.setString(2, fam);
            prepStat.setString(3, otch);
            prepStat.execute();
            query = "SELECT idsotrudnik FROM project.sotrudnik WHERE sotrudnik.fam = ? AND sotrudnik.name = ? AND sotrudnik.otch = ?;";
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, fam);
            prepStat.setString(2, name);
            prepStat.setString(3, otch);
            resSet = prepStat.executeQuery();
            resSet.next();
            idSotrudnik = resSet.getInt("idsotrudnik");
            prepStat = connection.prepareStatement("INSERT INTO project.user (login, pass, idsotrudnik, id_tip) VALUES (?, ?, ?, ?);");
            prepStat.setString(1, login);
            prepStat.setString(2, pass);
            prepStat.setInt(3, idSotrudnik);
            prepStat.setInt(4, id_tip);
            prepStat.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewZadacha(int idProject, String nazvanie) {
        int nomer = 0;
        try {
            prepStat = connection.prepareStatement("SELECT zadacha.nomer FROM project.project LEFT JOIN project.zadacha ON project.idproject = zadacha.idproject\n" +
                    "WHERE project.idproject = ? ORDER BY nomer DESC LIMIT 1;");
            prepStat.setInt(1, idProject);
            resSet = prepStat.executeQuery();
            resSet.next();
            nomer = resSet.getInt("nomer") + 1;
            prepStat = connection.prepareStatement("INSERT INTO project.zadacha (idproject, nomer, nazvanie) VALUES (?, ?, ?);");
            prepStat.setInt(1, idProject);
            prepStat.setInt(2, nomer);
            prepStat.setString(3, nazvanie);
            prepStat.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewPodzadacha(int idzadacha, String nazvanie, int idsotrudnik, String comment) {
        int nomer = 0;
        try {
            prepStat = connection.prepareStatement("SELECT podzadacha.nomer FROM project.podzadacha WHERE " +
                    "podzadacha.idzadacha = ? ORDER BY nomer DESC LIMIT 1;");
            prepStat.setInt(1, idzadacha);
            resSet = prepStat.executeQuery();
            resSet.next();
            nomer = resSet.getInt("nomer") + 1;
            prepStat = connection.prepareStatement("INSERT INTO project.podzadacha " +
                    "(idzadacha, nomer, nazvanie, idsotrudnik, datapostan, comment) \n" +
                    "VALUES (?, ?, ?, ?, ?, ?);");
            prepStat.setInt(1, idzadacha);
            prepStat.setInt(2, nomer);
            prepStat.setString(3, nazvanie);
            prepStat.setInt(4, idsotrudnik);
            prepStat.setDate(5, new  java.sql.Date(System.currentTimeMillis()));
            prepStat.setString(6, comment);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFinance(int idproject, int idtip_fin, BigDecimal summa, String comment) {
        try {
            prepStat = connection.prepareStatement("INSERT INTO project.finance (idproject, idtip_fin, date, summa, comment)" +
                    " VALUES (?, ?, ?, ?, ?);");
            prepStat.setInt(1, idproject);
            prepStat.setInt(2, idtip_fin);
            prepStat.setDate(3, new  java.sql.Date(System.currentTimeMillis()));
            prepStat.setBigDecimal(4, summa);
            prepStat.setString(5, comment);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewZakazchik(String nazvanie, String yr_adres, String inn) {
        try {
            prepStat = connection.prepareStatement("insert into project.zakazchik (nazvanie, yr_adres, inn) values (?, ?, ?); ");
            prepStat.setString(1, nazvanie);
            prepStat.setString(2, yr_adres);
            prepStat.setString(3, inn);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertHaracteristica(int idharacteristica, int idproject, String soderjanie) {
        try {
            prepStat = connection.prepareStatement("INSERT INTO project.project_haracteristica (idharacteristica, idproject, soderjanie) VALUES (?, ?, ?);");
            prepStat.setInt(1, idharacteristica);
            prepStat.setInt(2, idproject);
            prepStat.setString(3, soderjanie);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertHaracteristica(String nazvanie, int idproject, String soderjanie) {
        String query = "INSERT INTO project.haracteristica (nazvanie) VALUES (?);";
        String query2 = "SELECT haracteristica.idharacteristica FROM project.haracteristica\n" +
                "WHERE haracteristica.nazvanie = ?;";
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setString(1, nazvanie);
            prepStat.execute();
            prepStat = connection.prepareStatement(query2);
            prepStat.setString(1, nazvanie);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                idharacteristica = resSet.getInt(1);
            }
            prepStat = connection.prepareStatement("INSERT INTO project.project_haracteristica (idharacteristica, idproject, soderjanie) VALUES (?, ?, ?);");
            prepStat.setInt(1, idharacteristica);
            prepStat.setInt(2, idproject);
            prepStat.setString(3, soderjanie);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertNewProject(String nazvanie, String kod, String put, String ryad, String stolb, String polka,
                                 String comment, int idstatus, int idzakazchik, int idsotrudnik) {
        try {
            prepStat = connection.prepareStatement("INSERT INTO project.project ( nazvanie, kod, put, ryad, " +
                    "stolb, polka, comment, idstatus, idzakazchik, data) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            prepStat.setString(1, nazvanie);
            prepStat.setString(2, kod);
            prepStat.setString(3, put);
            prepStat.setString(4, ryad);
            prepStat.setString(5, stolb);
            prepStat.setString(6, polka);
            prepStat.setString(7, comment);
            prepStat.setInt(8, idstatus);
            prepStat.setInt(9, idzakazchik);
            prepStat.setDate(10, new  java.sql.Date(System.currentTimeMillis()));
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateComment(String kod, String comment) {
        try {
            prepStat = connection.prepareStatement("UPDATE project.project SET comment=? WHERE kod=?;");
            prepStat.setString(1, comment);
            prepStat.setString(2, kod);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePodzad(int idpodzadacha, String comment) {
        try {
            prepStat = connection.prepareStatement
                    ("UPDATE project.podzadacha SET idstatus_podzadachi='2', datazaver=?, comment=? WHERE idpodzadacha=?;");
            prepStat.setDate(1, new  java.sql.Date(System.currentTimeMillis()));
            prepStat.setString(2, comment);
            prepStat.setInt(3, idpodzadacha);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTipHaracteristica(String nazvanie) {
        try {
            prepStat = connection.prepareStatement("INSERT INTO project.haracteristica (nazvanie) VALUES (?);");
            prepStat.setString(1, nazvanie);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewTipDoc(String nazvanie) {
        try {
            prepStat = connection.prepareStatement("INSERT INTO project.tip_doc (nazvanie) VALUES (?);");
            prepStat.setString(1, nazvanie);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewTipUser(String nazvanie) {
        try {
            prepStat = connection.prepareStatement("INSERT INTO project.tip_user (nazvanie) VALUES (?);");
            prepStat.setString(1, nazvanie);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateArchiv(int idproject, String ryad, String stolb, String polka) {
        try {
            prepStat = connection.prepareStatement("UPDATE project.project SET ryad=?, stolb=?, polka=? WHERE idproject=?;");
            prepStat.setString(1, ryad);
            prepStat.setString(2, stolb);
            prepStat.setString(3, polka);
            prepStat.setInt(4, idproject);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHaracteristica(int idproject_haracteristica) {
        try {
            prepStat = connection.prepareStatement("DELETE FROM project.project_haracteristica WHERE project_haracteristica.idproject_haracteristica=?;");
            prepStat.setInt(1, idproject_haracteristica);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFinance(int idfinance) {
        try {
            prepStat = connection.prepareStatement("DELETE FROM project.finance WHERE finance.idfinance=?;");
            prepStat.setInt(1, idfinance);
            prepStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteZadacha(int nomer, int idproject, int idSelectedZad) {
        String query = "SELECT zadacha.idzadacha FROM project.zadacha WHERE zadacha.nomer > ? AND zadacha.idproject = ?;";
        String query2 = "SELECT zadacha.nomer FROM project.zadacha WHERE zadacha.nomer > ? AND zadacha.idproject = ?;";
        String query3 = "UPDATE project.zadacha SET zadacha.nomer=? WHERE zadacha.idzadacha=?;";
        masZadId = new ArrayList<Integer>();
        masZadNomer = new ArrayList<Integer>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, nomer);
            prepStat.setInt(2, idproject);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masZadId.add(resSet.getInt("idzadacha"));
            }
            prepStat = connection.prepareStatement(query2);
            prepStat.setInt(1, nomer);
            prepStat.setInt(2, idproject);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masZadNomer.add(resSet.getInt("nomer"));
            }
            if (masZadId.size() != 0){
                for (int i = 0; i<masZadId.size(); i++){
                    prepStat = connection.prepareStatement(query3);
                    prepStat.setInt(1, masZadNomer.get(i)-1);
                    prepStat.setInt(2, masZadId.get(i));
                    prepStat.execute();
                }
            }
            prepStat = connection.prepareStatement("DELETE FROM project.zadacha WHERE zadacha.idzadacha=?;");
            prepStat.setInt(1, idSelectedZad);
            prepStat.execute();
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePodzadacha(int nomer, int idSelectedZad, int idSelectedPodzad) {
        String query = "SELECT podzadacha.idpodzadacha FROM project.podzadacha WHERE podzadacha.nomer > ? AND podzadacha.idzadacha = ?;";
        String query2 = "SELECT podzadacha.nomer FROM project.podzadacha WHERE podzadacha.nomer > ? AND podzadacha.idzadacha = ?;";
        String query3 = "UPDATE project.podzadacha SET podzadacha.nomer=? WHERE podzadacha.idpodzadacha=?;";
        masPodzadId = new ArrayList<Integer>();
        masPodzadNomer = new ArrayList<Integer>();
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, nomer);
            prepStat.setInt(2, idSelectedZad);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masPodzadId.add(resSet.getInt("idpodzadacha"));
            }
            prepStat = connection.prepareStatement(query2);
            prepStat.setInt(1, nomer);
            prepStat.setInt(2, idSelectedZad);
            resSet = prepStat.executeQuery();
            while (resSet.next()) {
                masPodzadNomer.add(resSet.getInt("nomer"));
            }
            if (masPodzadId.size() != 0){
                for (int i = 0; i<masPodzadId.size(); i++){
                    prepStat = connection.prepareStatement(query3);
                    prepStat.setInt(1, masPodzadNomer.get(i)-1);
                    prepStat.setInt(2, masPodzadId.get(i));
                    prepStat.execute();
                }
            }
            prepStat = connection.prepareStatement("DELETE FROM project.podzadacha WHERE podzadacha.idpodzadacha=?;");
            prepStat.setInt(1, idSelectedPodzad);
            prepStat.execute();
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeNomerZadacha(int nomer, int idproject, int idSelectedZad, String side) {
        String query = "SELECT zadacha.idzadacha FROM project.zadacha WHERE zadacha.idproject = ? AND zadacha.nomer = ?;";
        String query2 = "UPDATE project.zadacha SET zadacha.nomer= ? WHERE zadacha.idzadacha= ?;";
        int idZadDo;
        int idZadPosle;
        try {
            if (side == "posle") {
                prepStat = connection.prepareStatement(query);
                prepStat.setInt(1, idproject);
                prepStat.setInt(2, nomer + 1);
                resSet = prepStat.executeQuery();
                resSet.next();
                idZadPosle = resSet.getInt("idzadacha");
                prepStat = connection.prepareStatement(query2);
                prepStat.setInt(1, nomer + 1);
                prepStat.setInt(2, idSelectedZad);
                prepStat.executeUpdate();
                prepStat = connection.prepareStatement(query2);
                prepStat.setInt(1, nomer);
                prepStat.setInt(2, idZadPosle);
                prepStat.executeUpdate();
                prepStat.close();
            }
            if (side == "do") {
                prepStat = connection.prepareStatement(query);
                prepStat.setInt(1, idproject);
                prepStat.setInt(2, nomer - 1);
                resSet = prepStat.executeQuery();
                resSet.next();
                idZadPosle = resSet.getInt("idzadacha");
                prepStat = connection.prepareStatement(query2);
                prepStat.setInt(1, nomer - 1);
                prepStat.setInt(2, idSelectedZad);
                prepStat.executeUpdate();
                prepStat = connection.prepareStatement(query2);
                prepStat.setInt(1, nomer);
                prepStat.setInt(2, idZadPosle);
                prepStat.executeUpdate();
                prepStat.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeNomerPodzadacha(int idSelectedZad,int idSelectedPodzad, int nomerSelectedPodzadad, String side) {
        String query = "SELECT podzadacha.idpodzadacha FROM project.podzadacha WHERE podzadacha.idzadacha = ? AND podzadacha.nomer = ?;";
        String query2 = "UPDATE project.podzadacha SET podzadacha.nomer= ? WHERE podzadacha.idpodzadacha= ?;";
        int idPodzadDo;
        int idPodzadPosle;
        try {
            if (side=="posle"){
                prepStat = connection.prepareStatement(query);
                prepStat.setInt(1, idSelectedZad);
                prepStat.setInt(2, nomerSelectedPodzadad+1);
                resSet = prepStat.executeQuery();
                resSet.next();
                idPodzadPosle = resSet.getInt("idpodzadacha");
                prepStat = connection.prepareStatement(query2);
                prepStat.setInt(1, nomerSelectedPodzadad+1);
                prepStat.setInt(2, idSelectedPodzad);
                prepStat.executeUpdate();
                prepStat = connection.prepareStatement(query2);
                prepStat.setInt(1, nomerSelectedPodzadad);
                prepStat.setInt(2, idPodzadPosle);
                prepStat.executeUpdate();
                prepStat.close();
            }
            if (side=="do"){
                prepStat = connection.prepareStatement(query);
                prepStat.setInt(1, idSelectedZad);
                prepStat.setInt(2, nomerSelectedPodzadad-1);
                resSet = prepStat.executeQuery();
                resSet.next();
                idPodzadPosle = resSet.getInt("idpodzadacha");
                prepStat = connection.prepareStatement(query2);
                prepStat.setInt(1, nomerSelectedPodzadad-1);
                prepStat.setInt(2, idSelectedPodzad);
                prepStat.executeUpdate();
                prepStat = connection.prepareStatement(query2);
                prepStat.setInt(1, nomerSelectedPodzadad);
                prepStat.setInt(2, idPodzadPosle);
                prepStat.executeUpdate();
                prepStat.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
