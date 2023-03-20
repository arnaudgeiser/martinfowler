package ch.hearc.ig.guideresto.persistence;

import ch.hearc.ig.guideresto.business.RestaurantType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DAORestaurantType {

    public static Set<RestaurantType> findAll() {
        try(Connection cnn = DBOracleConnection.openConnection();
            PreparedStatement statement = cnn.prepareStatement("SELECT NUMERO, LIBELLE, DESCRIPTION FROM TYPES_GASTRONOMIQUES")) {
            ResultSet resultSet = statement.executeQuery();
            Set<RestaurantType> restaurantTypes = new HashSet<>();
            while(resultSet.next()) {
                restaurantTypes.add(new RestaurantType(resultSet.getInt("NUMERO"),
                        resultSet.getString("LIBELLE"),
                        resultSet.getString("DESCRIPTION")));
            }
            resultSet.close();
            return restaurantTypes;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static RestaurantType findByNumero(int restaurantTypeNumero) {
        try(Connection cnn = DBOracleConnection.openConnection();
            PreparedStatement statement = cnn.prepareStatement("SELECT NUMERO, LIBELLE, DESCRIPTION FROM TYPES_GASTRONOMIQUES WHERE NUMERO = ?")) {
            statement.setInt(1, restaurantTypeNumero);
            ResultSet resultSet = statement.executeQuery();
            RestaurantType restaurantType = null;
            while (resultSet.next()) {
                restaurantType = new RestaurantType(resultSet.getInt("numero"), resultSet.getString("libelle"), resultSet.getString("description"));
            }
            resultSet.close();
            return restaurantType;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
