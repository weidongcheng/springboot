package com.wdc.repository;

import com.wdc.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by
 *
 * @author wdc
 * @date 2021/3/9.
 */
public interface AddressRepository  extends JpaRepository<Address,Long>{
}
