package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.SingletonConnection;
import metier.entities.Vetement;
public class VetementDaoImpl implements IVetementDao {
@Override
public Vetement save(Vetement p) {
Connection conn=SingletonConnection.getConnection();
try {
	PreparedStatement ps = conn.prepareStatement("SELECT * FROM habille WHERE NOM_VETEMENTS LIKE ?");
ps.setString(1, p.getNomVetement());
ps.setDouble(2, p.getPrix());
ps.executeUpdate();
PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(ID_Vetement) as MAX_ID FROM habille");

ResultSet rs =ps2.executeQuery();
if (rs.next()) {
p.setIdVetement(rs.getLong("MAX_ID"));
}
ps.close();
ps2.close();
} catch (SQLException e) {
e.printStackTrace();
}
return p;
}
@Override
public List<Vetement> VetementsParMC(String mc) {
List<Vetement> prods= new ArrayList<Vetement>();
Connection conn=SingletonConnection.getConnection();
try {
	PreparedStatement ps = conn.prepareStatement("select * from HABILLE where NOM_VETEMENTS LIKE ?");
ps.setString(1, "%"+mc+"%");
ResultSet rs = ps.executeQuery();
while (rs.next()) {
Vetement p = new Vetement();
p.setIdVetement(rs.getLong("ID_VETEMENTS"));
p.setNomVetement(rs.getString("NOM_VETEMENTS"));
p.setPrix(rs.getDouble("PRIX"));
prods.add(p);
}
} catch (SQLException e) {
e.printStackTrace();
}
return prods;
}
@Override
public Vetement getVetement(Long id) {
Connection conn=SingletonConnection.getConnection();
Vetement p = new Vetement();
try {
	PreparedStatement ps = conn.prepareStatement("select * from habille where ID_VETEMENTS = ?");
ps.setLong(1, id);
ResultSet rs = ps.executeQuery();
if (rs.next()) {
p.setIdVetement(rs.getLong("ID_VETEMENTS"));
p.setNomVetement(rs.getString("NOM_VETEMENTS"));
p.setPrix(rs.getDouble("PRIX"));
}
} catch (SQLException e) {
e.printStackTrace();
}
return p;
}
@Override
public Vetement updateVetement(Vetement p) {
Connection conn=SingletonConnection.getConnection();
try {
	PreparedStatement ps = conn.prepareStatement("UPDATE habille SET NOM_VETEMENTS=?,PRIX=? WHERE ID_VETEMENTS=?");
ps.setString(1, p.getNomVetement());
ps.setDouble(2, p.getPrix());
ps.setLong(3, p.getIdVetement());
ps.executeUpdate();
ps.close();
} catch (SQLException e) {
e.printStackTrace();
}
return p;
}
@Override
public void deleteVetement(Long id) {
Connection conn=SingletonConnection.getConnection();
try {
	PreparedStatement ps = conn.prepareStatement("DELETE FROM habille WHERE ID_VETEMENTS = ?");
ps.setLong(1, id);
ps.executeUpdate();
ps.close();
} catch (SQLException e) {
e.printStackTrace();
}
}}