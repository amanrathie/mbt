<#include "layout/layout.ftl">

<#assign breadcrumb>
    <a href="#">Administrativo</a> > <a href="#">Painel Geral de Avaliações</a>
</#assign>

<@admin titulo="Painel Geral de Avaliações" breadcrumb=breadcrumb>
	<section class="status-do-filtro ativo dmb-30">
			<div class="status-do-filtro-inativo">
				<div class="block-rate position-relative block-rate--gray">
					<h4 class="block-rate__title-iconed btn-mostrar-filtro"><i class="fa fa-filter" aria-hidden="true"></i>Filtros de pesquisa</h4>
				</div>
			</div>
			<div class="status-do-filtro-ativo">
				<div class="block-rate position-relative block-rate--gray">
					<h4 class="block-rate__title-iconed"><i class="fa fa-filter" aria-hidden="true"></i>Filtros de pesquisa</h4>
					<a class="btn-mostrar-filtro a--position-left dml-300 dmt-22"><b>Ocultar filtros</b></a>
					<div class="dmt-30 dmb-30 dml-20 dmr-20">
						<form action="#" method="post">
							<fieldset>
								<div class="row">
									<div class="col-xs-3">
										<label for="tipo-de-avaliacao">Tipo de avaliação</label>
										<select id="tipo-de-avaliacao" name="tipo-de-avaliacao" class="form-control">
											<option value="">Selecione</option>
											<option value="Todos">Todos</option>
											<option value="Avaliação Independente">Avaliação Independente</option>
											<option value="Avaliação Cidadã">Avaliação Cidadã</option>
											<option value="Autoavaliação da Gestão">Autoavaliação da Gestão</option>
										</select>
									</div>
									<div class="col-xs-3">
										<label for="fase-da-avaliacao">Fase da avaliação</label>
										<select id="fase-da-avaliacao" name="fase-da-avaliacao" class="form-control">
											<option value="">Selecione</option>
											<option value="Todos">Todos</option>
											<option value="Questionário em aprovação">Questionário em aprovação</option>
											<option value="Questionário aprovado (sem andamento)">Questionário aprovado (sem andamento)</option>
											<option value="Em planejamento">Em planejamento</option>
											<option value="Em andamento">Em andamento</option>
											<option value="Aguardando publicação">Aguardando publicação</option>
											<option value="Publicada">Publicada</option>
										</select>
									</div>
									<div class="col-xs-3">
										<label for="poder">Poder</label>
										<select id="poder" name="poder" class="form-control">
											<option value="">Selecione</option>
											<option value="Todos">Todos</option>
											<option value="Legislativo">Legislativo</option>
											<option value="Judiciário">Judiciário</option>
											<option value="Executivo">Executivo</option>
											<option value="Nenhum">Nenhum</option>
										</select>
									</div>
									<div class="col-xs-3">
										<label for="status-da-avaliacao">Status da avaliação</label>
										<select id="status-da-avaliacao" name="status-da-avaliacao" class="form-control">
											<option value="">Selecione</option>
											<option value="Todos">Todos</option>
											<option value="Ativo">Ativo</option>
											<option value="Inativo">Inativo</option>
										</select>
									</div>
								</div>
								<div class="row dmt-30">
									<div class="col-xs-6">
										<div class="block-filter__form-search full-width" action="#" method="post">
											<fieldset>

												<label for="block-filter__form-search__text" class="sr-only">Pesquise por entidade responsável</label>
												<input type="text" class="form-control" id="block-filter__form-search__text" placeholder="Pesquise por entidade responsável">
												<button type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>

											</fieldset>
										</div>
									</div>
									<div class="col-xs-6">
										<input type="submit" class="button--secondary-a pull-right dml-10" value="Filtrar">
										<a href="#" class="button--icon-a button--bigger button-print pull-right"><i class="fa fa-print" aria-hidden="true"></i> Imprimir</a>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</section>

		<section class="block-filter-chart">
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="avaliador-2">
					<div class="dmb-30 block-painel">
						<div class="block-painel__top">
							<div class="row">
								<div class="col-xs-7">
									<a href="#" class="title-section">Nome da Avaliação Dolor Dit Ame</a>
									<div class="row dmt-5">
										<div class="col-xs-3">
											<strong>Executivo</strong>
										</div>
										<div class="col-xs-4">
											<strong>Entidade responsável</strong>
										</div>
										<div class="col-xs-5">
											<p>mail@mail.com | (00) 0000 0000</p>
										</div>
									</div>
								</div>
								<div class="col-xs-5 text-right">
									<a href="kanban.php" class="button--primary-a dmt-10 dmr-10">Ver minhas atividades</a>
									<a href="dashboard_avaliacao_independente.php" class="button--primary-a dmt-10">Acompanhar</a>
								</div>
							</div>
						</div>
						<div class="dpt-20 dpb-20 dpl-20 dpr-20">
							<div class="row">
								<div class="col-xs-2">
									<div class="block-painel__calendario">
										<p><small>Início 00/00/0000</small></p>
										<p><small>Término 00/00/0000</small></p>
									</div>
								</div>
								<div class="col-xs-2 dpt-10 ">
									<div class="toggle-wrapper">
										<p class="toggle-text">Ativa</p>
										<label class="toggle">
											<input type="checkbox"/><div></div>
										</label>
										<p class="toggle-text">Inativa</p>
									</div>
								</div>
								<div class="col-xs-3 text-center">
									<p class="block-painel__avaliacao block-painel__avaliacao-back-yellow">Avaliação independente</p>
									<p><small><i>Em andamento</i></small></p>
								</div>
								<div class="col-xs-5">
									<div class="row">
										<div class="col-xs-4 text-center">
											<p>Etapa de Avaliação</p>
											<span class="block-painel__meter back-green">Faltam 10 dias úteis</span>
										</div>
										<div class="col-xs-4 text-center">
											<p>Etapa de Revisão</p>
											<span class="block-painel__meter back-orange">Faltam 18 dias úteis</span>
										</div>
										<div class="col-xs-4 text-center">
											<p>Etapa de Validação</p>
											<span class="block-painel__meter back-green">Faltam 34 dias úteis</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="dmb-30 block-painel">
						<div class="block-painel__top">
							<div class="row">
								<div class="col-xs-7">
									<a href="#" class="title-section">Nome da Avaliação Dolor Dit Ame</a>
									<div class="row dmt-5">
										<div class="col-xs-3">
											<strong>Executivo</strong>
										</div>
										<div class="col-xs-4">
											<strong>Entidade responsável</strong>
										</div>
										<div class="col-xs-5">
											<p>mail@mail.com | (00) 0000 0000</p>
										</div>
									</div>
								</div>
								<div class="col-xs-5 text-right">
								</div>
							</div>
						</div>
						<div class="dpt-20 dpb-20 dpl-20 dpr-20">
							<div class="row">
								<div class="col-xs-2">
									<div class="block-painel__calendario">
										<p><small>Início 00/00/0000</small></p>
										<p><small>Término 00/00/0000</small></p>
									</div>
								</div>
								<div class="col-xs-2 dpt-10">
									<div class="toggle-wrapper">
										<p class="toggle-text">Ativa</p>
										<label class="toggle">
											<input type="checkbox"/><div></div>
										</label>
										<p class="toggle-text">Inativa</p>
									</div>
								</div>
								<div class="col-xs-3 text-center">
									<p class="block-painel__avaliacao block-painel__avaliacao-back-yellow">Avaliação independente</p>
									<p><small><i>Aguardando validação do questionário</i></small></p>
									<p><small>Envio para validação 00/00/0000</small></p>
								</div>
								<div class="col-xs-5">
									<div class="row">
										<div class="col-xs-4 text-center">
											<p>Etapa de Avaliação</p>
											<span class="block-painel__meter back-green">Faltam 10 dias úteis</span>
										</div>
										<div class="col-xs-4 text-center">
											<p>Etapa de Revisão</p>
											<span class="block-painel__meter back-orange">Faltam 18 dias úteis</span>
										</div>
										<div class="col-xs-4 text-center">
											<p>Etapa de Validação</p>
											<span class="block-painel__meter back-green">Faltam 34 dias úteis</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="dmb-30 block-painel">
						<div class="block-painel__top">
							<div class="row">
								<div class="col-xs-7">
									<a href="#" class="title-section">Nome da Avaliação Dolor Dit Ame</a>
									<div class="row dmt-5">
										<div class="col-xs-3">
											<strong>Executivo</strong>
										</div>
										<div class="col-xs-4">
											<strong>Entidade responsável</strong>
										</div>
										<div class="col-xs-5">
											<p>mail@mail.com | (00) 0000 0000</p>
										</div>
									</div>
								</div>
								<div class="col-xs-5 text-right">
									<a href="kanban.php" class="button--primary-a dmt-10 dmr-10">Ver minhas atividades</a>
									<a href="dashboard_avaliacao_independente.php" class="button--primary-a dmt-10">Acompanhar</a>
								</div>
							</div>
						</div>
						<div class="dpt-20 dpb-20 dpl-20 dpr-20">
							<div class="row">
								<div class="col-xs-2">
									<div class="block-painel__calendario">
										<p><small>Início 00/00/0000</small></p>
										<p><small>Término 00/00/0000</small></p>
									</div>
								</div>
								<div class="col-xs-2 dpt-10">
									<div class="toggle-wrapper">
										<p class="toggle-text">Ativa</p>
										<label class="toggle">
											<input type="checkbox"/><div></div>
										</label>
										<p class="toggle-text">Inativa</p>
									</div>
								</div>
								<div class="col-xs-3 text-center">
									<p class="block-painel__avaliacao block-painel__avaliacao-back-yellow">Avaliação independente</p>
									<p><small><i>Avaliação realizada</i></small></p>
								</div>
								<div class="col-xs-5">
									<div class="row">
										<div class="col-xs-4 text-center">
											<p>Etapa de Avaliação</p>
											<span class="block-painel__meter back-green">Faltam 10 dias úteis</span>
										</div>
										<div class="col-xs-4 text-center">
											<p>Etapa de Revisão</p>
											<span class="block-painel__meter back-orange">Faltam 18 dias úteis</span>
										</div>
										<div class="col-xs-4 text-center">
											<p>Etapa de Validação</p>
											<span class="block-painel__meter back-green">Faltam 34 dias úteis</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="dmb-30 block-painel">
						<div class="block-painel__top">
							<div class="row">
								<div class="col-xs-7">
									<a href="#" class="title-section">Nome da Avaliação Dolor Dit Ame</a>
									<div class="row dmt-5">
										<div class="col-xs-3">
											<strong>Executivo</strong>
										</div>
										<div class="col-xs-4">
											<strong>Entidade responsável</strong>
										</div>
										<div class="col-xs-5">
											<p>mail@mail.com | (00) 0000 0000</p>
										</div>
									</div>
								</div>
								<div class="col-xs-5 text-right">
									<a href="kanban.php" class="button--primary-a dmt-10 dmr-10">Ver minhas atividades</a>
									<a href="dashboard_avaliacao_independente.php" class="button--primary-a dmt-10">Acompanhar</a>
								</div>
							</div>
						</div>
						<div class="dpt-20 dpb-20 dpl-20 dpr-20">
							<div class="row">
								<div class="col-xs-2">
									<div class="block-painel__calendario">
										<p><small>Início 00/00/0000</small></p>
										<p><small>Término 00/00/0000</small></p>
									</div>
								</div>
								<div class="col-xs-2 dpt-10">
									<div class="toggle-wrapper">
										<p class="toggle-text">Ativa</p>
										<label class="toggle">
											<input type="checkbox"/><div></div>
										</label>
										<p class="toggle-text">Inativa</p>
									</div>
								</div>
								<div class="col-xs-3 text-center">
									<p class="block-painel__avaliacao block-painel__avaliacao-back-yellow">Avaliação independente</p>
									<p><small><i>Avaliação realizada</i></small></p>
								</div>
								<div class="col-xs-5">
									<div class="row">
										<div class="col-xs-4 text-center">
											<p>Etapa de Avaliação</p>
											<span class="block-painel__meter back-green">Faltam 10 dias úteis</span>
										</div>
										<div class="col-xs-4 text-center">
											<p>Etapa de Revisão</p>
											<span class="block-painel__meter back-orange">Faltam 18 dias úteis</span>
										</div>
										<div class="col-xs-4 text-center">
											<p>Etapa de Validação</p>
											<span class="block-painel__meter back-green">Faltam 34 dias úteis</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="dmb-30 block-painel">
						<div class="block-painel__top">
							<div class="row">
								<div class="col-xs-7">
									<a href="#" class="title-section">Nome da Avaliação Dolor Dit Ame</a>
									<div class="row dmt-5">
										<div class="col-xs-3">
											<strong>Executivo</strong>
										</div>
										<div class="col-xs-4">
											<strong>Entidade responsável</strong>
										</div>
										<div class="col-xs-5">
											<p>mail@mail.com | (00) 0000 0000</p>
										</div>
									</div>
								</div>
								<div class="col-xs-5 text-right">
									<a href="dashboard_avaliacao_independente.php" class="button--primary-a dmt-10">Acompanhar</a>
								</div>
							</div>
						</div>
						<div class="dpt-20 dpb-20 dpl-20 dpr-20">
							<div class="row">
								<div class="col-xs-2">
									<div class="block-painel__calendario">
										<p><small>Início 00/00/0000</small></p>
										<p><small>Término 00/00/0000</small></p>
									</div>
								</div>
								<div class="col-xs-2 dpt-10">
									<div class="toggle-wrapper">
										<p class="toggle-text">Ativa</p>
										<label class="toggle">
											<input type="checkbox"/><div></div>
										</label>
										<p class="toggle-text">Inativa</p>
									</div>
								</div>
								<div class="col-xs-3 text-center">
									<p class="block-painel__avaliacao block-painel__avaliacao-back-yellow">Avaliação independente</p>
									<p><small><i>Em andamento</i></small></p>
								</div>
								<div class="col-xs-5">
									<div class="row">
										<div class="col-xs-4 text-center">
											<p>Etapa de Avaliação</p>
											<span class="block-painel__meter back-green">Faltam 10 dias úteis</span>
										</div>
										<div class="col-xs-4 text-center">
											<p>Etapa de Revisão</p>
											<span class="block-painel__meter back-orange">Faltam 18 dias úteis</span>
										</div>
										<div class="col-xs-4 text-center">
											<p>Etapa de Validação</p>
											<span class="block-painel__meter back-green">Faltam 34 dias úteis</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="dmb-30 block-painel">
						<div class="block-painel__top">
							<div class="row">
								<div class="col-xs-7">
									<a href="#" class="title-section">Nome da Avaliação Dolor Dit Ame</a>
									<div class="row dmt-5">
										<div class="col-xs-3">
											<strong>Executivo</strong>
										</div>
										<div class="col-xs-4">
											<strong>Usuário responsável</strong>
										</div>
										<div class="col-xs-5">
											<p>mail@mail.com | (00) 0000 0000</p>
										</div>
									</div>
								</div>
								<div class="col-xs-5 text-right">
									<a href="dashboard_avaliacao_independente.php" class="button--primary-a dmt-10">Acompanhar</a>
								</div>
							</div>
						</div>
						<div class="dpt-20 dpb-20 dpl-20 dpr-20">
							<div class="row">
								<div class="col-xs-2">
									<div class="block-painel__calendario">
										<p><small>Início 00/00/0000</small></p>
									</div>
								</div>
								<div class="col-xs-2 dpt-10">
									<div class="toggle-wrapper">
										<p class="toggle-text">Ativa</p>
										<label class="toggle">
											<input type="checkbox"/><div></div>
										</label>
										<p class="toggle-text">Inativa</p>
									</div>
								</div>
								<div class="col-xs-3 text-center">
									<p class="block-painel__avaliacao block-painel__avaliacao-back-red">Avaliação cidadã</p>
									<p><small><i>Em excução</i></small></p>
								</div>
								<div class="col-xs-5">
									<div class="row">
										<div class="col-xs-4 text-center">
											<p><small><i>Estados participantes</i></small></p>
											<p><strong>00</strong></p>
										</div>
										<div class="col-xs-4 text-center">
											<p><small><i>Municípios participantes</i></small></p>
											<p><strong>0.000</strong></p>
										</div>
										<div class="col-xs-4 text-center">
											<p><small><i>Questionários respondidos</i></small></p>
											<p><strong>0.000</strong></p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="dmb-30 block-painel">
						<div class="block-painel__top">
							<div class="row">
								<div class="col-xs-7">
									<a href="#" class="title-section">Nome da Avaliação Dolor Dit Ame</a>
									<div class="row dmt-5">
										<div class="col-xs-3">
											<strong>Executivo</strong>
										</div>
										<div class="col-xs-4">
											<strong>Usuário responsável</strong>
										</div>
										<div class="col-xs-5">
											<p>mail@mail.com | (00) 0000 0000</p>
										</div>
									</div>
								</div>
								<div class="col-xs-5 text-right">
									<a href="dashboard_avaliacao_independente.php" class="button--primary-a dmt-10">Acompanhar</a>
								</div>
							</div>
						</div>
						<div class="dpt-20 dpb-20 dpl-20 dpr-20">
							<div class="row">
								<div class="col-xs-2">
									<div class="block-painel__calendario">
										<p><small>Início 00/00/0000</small></p>
									</div>
								</div>
								<div class="col-xs-2 dpt-10">
									<div class="toggle-wrapper">
										<p class="toggle-text">Ativa</p>
										<label class="toggle">
											<input type="checkbox"/><div></div>
										</label>
										<p class="toggle-text">Inativa</p>
									</div>
								</div>
								<div class="col-xs-3 text-center">
									<p class="block-painel__avaliacao block-painel__avaliacao-back-red">Avaliação cidadã</p>
									<p><small><i>Em planejamento</i></small></p>
								</div>
								<div class="col-xs-5">
									<div class="row">
										<div class="col-xs-4 text-center">
											<p><small><i>Estados participantes</i></small></p>
											<p><strong>00</strong></p>
										</div>
										<div class="col-xs-4 text-center">
											<p><small><i>Municípios participantes</i></small></p>
											<p><strong>0.000</strong></p>
										</div>
										<div class="col-xs-4 text-center">
											<p><small><i>Questionários respondidos</i></small></p>
											<p><strong>0.000</strong></p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="dmb-30 block-painel">
						<div class="block-painel__top">
							<div class="row">
								<div class="col-xs-7">
									<a href="#" class="title-section">Nome da Avaliação Dolor Dit Ame</a>
									<div class="row dmt-5">
										<div class="col-xs-3">
											<strong>Executivo</strong>
										</div>
										<div class="col-xs-4">
											<strong>Usuário responsável</strong>
										</div>
										<div class="col-xs-5">
											<p>mail@mail.com | (00) 0000 0000</p>
										</div>
									</div>
								</div>
								<div class="col-xs-5 text-right">
									<a href="dashboard_avaliacao_independente.php" class="button--primary-a dmt-10">Acompanhar</a>
								</div>
							</div>
						</div>
						<div class="dpt-20 dpb-20 dpl-20 dpr-20">
							<div class="row">
								<div class="col-xs-2">
									<div class="block-painel__calendario">
										<p><small>Início 00/00/0000</small></p>
									</div>
								</div>
								<div class="col-xs-2 dpt-10">
									<div class="toggle-wrapper">
										<p class="toggle-text">Ativa</p>
										<label class="toggle">
											<input type="checkbox"/><div></div>
										</label>
										<p class="toggle-text">Inativa</p>
									</div>
								</div>
								<div class="col-xs-3 text-center">
									<p class="block-painel__avaliacao block-painel__avaliacao-back-purple">Autoavaliação da Gestão</p>
									<p><small><i>Em excução</i></small></p>
								</div>
								<div class="col-xs-5">
									<div class="row">
										<div class="col-xs-4 text-center">
											<p><small><i>Estados participantes</i></small></p>
											<p><strong>00/00</strong></p>
										</div>
										<div class="col-xs-4 text-center">
											<p><small><i>Municípios participantes</i></small></p>
											<p><strong>0.000/00.000</strong></p>
										</div>
										<div class="col-xs-4 text-center">
											<p><small><i>Foram revisadas</i></small></p>
											<p><strong>0.000</strong></p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="dmb-30 block-painel">
						<div class="block-painel__top">
							<div class="row">
								<div class="col-xs-7">
									<a href="#" class="title-section">Nome da Avaliação Dolor Dit Ame</a>
									<div class="row dmt-5">
										<div class="col-xs-3">
											<strong>Executivo</strong>
										</div>
										<div class="col-xs-4">
											<strong>Usuário responsável</strong>
										</div>
										<div class="col-xs-5">
											<p>mail@mail.com | (00) 0000 0000</p>
										</div>
									</div>
								</div>
								<div class="col-xs-5 text-right">
									<a href="dashboard_avaliacao_independente.php" class="button--primary-a dmt-10">Acompanhar</a>
								</div>
							</div>
						</div>
						<div class="dpt-20 dpb-20 dpl-20 dpr-20">
							<div class="row">
								<div class="col-xs-2">
									<div class="block-painel__calendario">
										<p><small>Início 00/00/0000</small></p>
									</div>
								</div>
								<div class="col-xs-2 dpt-10">
									<div class="toggle-wrapper">
										<p class="toggle-text">Ativa</p>
										<label class="toggle">
											<input type="checkbox"/><div></div>
										</label>
										<p class="toggle-text">Inativa</p>
									</div>
								</div>
								<div class="col-xs-3 text-center">
									<p class="block-painel__avaliacao block-painel__avaliacao-back-purple">Autoavaliação da Gestão</p>
									<p><small><i>Em planejamento</i></small></p>
								</div>
								<div class="col-xs-5">
									<div class="row">
										<div class="col-xs-4 text-center">
											<p><small><i>Estados participantes</i></small></p>
											<p><strong>00/00</strong></p>
										</div>
										<div class="col-xs-4 text-center">
											<p><small><i>Municípios participantes</i></small></p>
											<p><strong>0.000/00.000</strong></p>
										</div>
										<div class="col-xs-4 text-center">
											<p><small><i>Foram revisadas</i></small></p>
											<p><strong>0.000</strong></p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="block-pagination">
						<div class="block-pagination__list">
							<span>Página 16 de 1834</span>

							<ul>
								<li><a href="#" class="list__previous"><i class="fa fa-fw fa-caret-left"></i> Anterior</a></li>
								<li><a href="#">15</a></li>
								<li><a href="#" class="active">16</a></li>
								<li><a href="#">17</a></li>
								<li><a href="#" class="middle">...</a></li>
								<li><a href="#">1832</a></li>
								<li><a href="#">1833</a></li>
								<li><a href="#">1834</a></li>
								<li><a href="#" class="list__next">Próxima <i class="fa fa-fw fa-caret-right"></i></a></li>
							</ul>
						</div>

						<form class="block-pagination__form-show" action="#" method="post">
							<fieldset>
								<legend>Formulário de exibição de linhas</legend>

								<label for="block-pagination__form-search__list-4">Exibir</label>
								<select id="block-pagination__form-search__list-4">
									<option value="">&nbsp;</option>
									<option value="10 linhas">10 linhas</option>
									<option value="50 linhas">50 linhas</option>
									<option value="100 linhas">100 linhas</option>
								</select>

								<input type="submit" value="Exibir" class="sr-only">
							</fieldset>
						</form>

						<form class="block-pagination__form-search" action="#" method="post">
							<fieldset>
								<legend>Formulário de busca de páginas</legend>

								<label for="block-pagination__form-search__text-4">Ir para a página:</label>
								<input type="text" id="block-pagination__form-search__text-4">
								<input type="submit" value="IR">
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</section>
</@admin>