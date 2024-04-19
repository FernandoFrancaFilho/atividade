package com.aula.demo.repositories;

import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoRepository extends JpaRepositoryExtension<Pedido, Long> {

}

