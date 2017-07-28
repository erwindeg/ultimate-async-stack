import { MovieClientPage } from './app.po';

describe('movie-client App', () => {
  let page: MovieClientPage;

  beforeEach(() => {
    page = new MovieClientPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
