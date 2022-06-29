import { ActiveUserPipe } from './active-user.pipe';

describe('ActiveUserPipe', () => {
  it('create an instance', () => {
    const pipe = new ActiveUserPipe();
    expect(pipe).toBeTruthy();
  });
});
