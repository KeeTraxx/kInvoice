import {Injectable} from '@angular/core';
import {Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {UserSettings} from "./usersettings";

@Injectable()
export class SettingsService {

  constructor(private http: Http) {
  }


  public getSettings() {
    return this.http.get('/api/user/settings').toPromise().then(res => res.json() as UserSettings);
  }

  public saveSettings(settings:UserSettings) {
    return this.http.post('/api/user/settings', settings).toPromise().then(res => res.json() as UserSettings);
  }


}
