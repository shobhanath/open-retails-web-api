package com.openretails.stock.manager;

import com.openretails.data.Collections;
import com.openretails.data.UserDTO;

public interface ProductCategoryManager {

	Collections<UserDTO> create(Collections<UserDTO> users);
}
