import { Component, computed, effect, signal } from '@angular/core';
import {
  Router,
  RouterLink,
  RouterLinkActive,
  RouterOutlet,
} from '@angular/router';
import { SecurityContextService } from './services/security-context-service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.component.html',
  styles: [],
})
export class AppComponent {
  anonymous = computed(() => !this.security.loginUser());
  activated = computed(() => this.security.loginUser()?.activated);

  showMenu = computed<boolean | undefined>(
    () => !this.anonymous() && this.activated()
  );

  constructor(
    private security: SecurityContextService,
    private router: Router
  ) {
    if (!security.loginUser()) {
      router.navigate(['/signin']);
    }
  }

  signOut() {
    this.security.loginUser.set(undefined);
    this.router.navigate(['/signin']);
  }
}
