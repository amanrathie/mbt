const PesquisaPainelGeralAvaliacoes = {
		configurarGrid: function() {
			$.ajax({
				url:'/mbt/admin/api/auth/painel_geral_avaliacoes',
				success: function(data){
					console.log(data);
				}				
			});
    },

    init: function() {
        this.configurarGrid();        
    }
};