import { Component, OnInit } from '@angular/core';
import {UserSettings} from "../usersettings";
import {SettingsService} from "../settings.service";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  alerts:any[] = [];

  settings:UserSettings = {
    invoiceAddress: {
      city: '',
      street: '',
      company: '',
      name: '',
      zip: ''
    },
    invoiceAccount: ''
  };

  constructor(private settingsService:SettingsService) { }

  ngOnInit() {
    this.settingsService.getSettings().then(settings => this.settings = settings).catch((err) => {});
  }

  save() {
    this.settingsService.saveSettings(this.settings)
      .then(() => this.alerts.push({msg: 'Settings saved!', type: 'success'}))
      .catch(() => this.alerts.push({msg: 'Error saving settings!', type: 'danger'}));
  }

}
