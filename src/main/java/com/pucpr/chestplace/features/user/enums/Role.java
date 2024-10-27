package com.pucpr.chestplace.features.user.enums;


import java.util.Arrays;
import java.util.List;

public enum Role {
    VENDEDOR(Arrays.asList(
            Permission.CADASTRAR_PRODUTO, Permission.EDITAR_PRODUTO,
            Permission.VISUALIZAR_PRODUTO, Permission.EXCLUIR_PRODUTO,
            Permission.VISUALIZAR_USUARIO, Permission.VISUALIZAR_VENDAS,
            Permission.ATUALIZAR_VENDA, Permission.EDITAR_USUARIO)),
            
    COMPRADOR(Arrays.asList(
            Permission.VISUALIZAR_PRODUTO, Permission.REALIZAR_COMPRA,
            Permission.VISUALIZAR_COMPRAS, Permission.CADASTRAR_USUARIO,
            Permission.EDITAR_USUARIO, Permission.EXCLUIR_USUARIO,
            Permission.VISUALIZAR_USUARIO));


    private List<Permission> permissionList;

    Role(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }


    public List<Permission> getPermissionsList() {
        return permissionList;
    }

    public void setPermissionsList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
