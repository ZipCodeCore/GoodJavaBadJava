package rocks.zipcode.blozzer.service;

import java.util.List;

import rocks.zipcode.blozzer.payload.UserDTO;

public interface UserService {
	UserDTO createUser(UserDTO userDTO);
	UserDTO updateUser(UserDTO userDTO, Integer userID);
	UserDTO getUserById(Integer userId);
	List<UserDTO> getAllUsers();
	void deleteUser(Integer userId);
}
