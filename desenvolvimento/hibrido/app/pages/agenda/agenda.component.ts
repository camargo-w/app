import { Component }  from '@angular/core';
import { NgClass, DatePipe } from '@angular/common';

import { AngularFire, FirebaseListObservable } from 'angularfire2';

import { Observable } from 'rxjs/Observable';

import { NavController, ActionSheetController, Platform, AlertController } from 'ionic-angular';
import { InAppBrowser }  from 'ionic-native';

import { AgendaFilterPipe } from './';
import { PreferenciaPage } from '../preferencia';
import { MapaAgendaPage } from '../mapa-agenda';
import { RotaPage } from '../rota';
import { AgendaDetailPage } from '../agenda-detail';

import { AgendaService } from '../../providers/data';
import { GlobalMethodService, IAgenda } from '../shared';

@Component({
  templateUrl: 'build/pages/agenda/agenda.component.html',
  pipes: [AgendaFilterPipe],
  directives: [NgClass]
})
export class AgendaPage {

  titulo: string = 'Agendas';
  filtro: string = '';
  mensagenErro: any = null;

  constructor(
    public _navCtrl: NavController,
    public _platform: Platform,
    public _globalMethod: GlobalMethodService,
    public _service: AgendaService,
    public _alertCtrl: AlertController,
    public _actionSheetCtrl: ActionSheetController) {
  }

  ionViewLoaded() {
    this._service.filterByDate((new DatePipe()).transform(new Date(), 'yyyy-MM-dd'), false);
  }

  marcarComoFavorito(agenda: IAgenda): void {
    agenda.favorito = !agenda.favorito;
    this._service.update(agenda, { favorito: agenda.favorito });
  }

  carregarPreferencias(): void {
    this._globalMethod.carregarPagina(PreferenciaPage, this.titulo, true, this._navCtrl);
  }

  carregarMapa(agenda: IAgenda): void {
    // this._navCtrl.push(MapaAgendaPage, agenda);
    new InAppBrowser(`http://maps.google.com/maps?q=${agenda.descricao}`, '_blank');
  }

  carregarRotas(agenda: IAgenda): void {
    this._globalMethod.carregarPagina(RotaPage, agenda.$key, true, this._navCtrl);
  }

  editar(agenda: IAgenda): void {
    this._globalMethod.carregarPagina(AgendaDetailPage, { titulo: 'Editar', agenda: agenda }, true, this._navCtrl);
  }

  incluir(): void {
    this._globalMethod.carregarPagina(AgendaDetailPage, { titulo: 'Criar', agenda: null }, true, this._navCtrl);
  }

  gerenciar(agenda: IAgenda): void {
    let actionSheet = this._actionSheetCtrl.create({
      title: 'Opções',
      buttons: [
        {
          text: 'Excluir',
          role: 'destructive',
          icon: !this._platform.is('ios') ? 'trash' : null,
          handler: () => {
            this.excluir(agenda);
          }
        },
        {
          text: 'Editar',
          icon: !this._platform.is('ios') ? 'create' : null,
          handler: () => {
            this.editar(agenda);
          }
        },
        {
          text: 'Compartilhar',
          icon: !this._platform.is('ios') ? 'share' : null,
          handler: () => {
            // -- TODO
            console.log('Compartilhar clicked');
          }
        },
        {
          text: 'Cancelar',
          role: 'cancel',
          icon: !this._platform.is('ios') ? 'close' : null,
          handler: () => {
            console.log('Cancelar clicked');
          }
        }
      ]
    });
    actionSheet.present();
  }

  excluir(agenda: IAgenda): void {
    let confirm = this._alertCtrl.create({
      title: 'Excluir',
      message: `Deseja realmente excluir agenda ${agenda.descricao}?`,
      buttons: [
        {
          text: 'Não',
          handler: () => {
            console.log('Não clicked');
          }
        },
        {
          text: 'Sim',
          handler: () => {
            // // -- TODO Otimizar a remoção de agenda da lista local
            // this._service.remove(agenda).then(() => {
            //   // -- TODO Toast com msg remoção
            // });
            console.log('Sim clicked');
          }
        }
      ]
    });
    confirm.present();
  }

}
