# Github

## Issues 

1. 프로젝트 `Repository` 의 `Navbar`에 있는 `Issues`를 클릭합니다.

![Issues 작성1](https://github-production-user-asset-6210df.s3.amazonaws.com/74192619/264818342-b25a8f59-43ee-4339-a5b2-bfff99b04fe9.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20230901%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20230901T000932Z&X-Amz-Expires=300&X-Amz-Signature=c96d808ac9da21b2f5e33b18b316dc6aea7b80d33b337f0cf646643b1b5138bf&X-Amz-SignedHeaders=host&actor_id=74192619&key_id=0&repo_id=685011150)

2. 오른쪽 상단에 보이는 `New issue` 를 클릭합니다.

![Issues 작성2](https://github-production-user-asset-6210df.s3.amazonaws.com/74192619/264818461-36acffc7-8104-4002-9adf-2b9ab063911c.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20230901%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20230901T001038Z&X-Amz-Expires=300&X-Amz-Signature=69a595afc12f8cc347d3c26964db4a771491f04de09e75f189d55ceb000f2a47&X-Amz-SignedHeaders=host&actor_id=74192619&key_id=0&repo_id=685011150)

3. `기능 제안` 혹은 `버그 발생` 중 원하는 이슈를 선택합니다.

![2023-09-01_09-12-21](https://github-production-user-asset-6210df.s3.amazonaws.com/74192619/264818663-fdee1123-fbb9-4150-ae5c-66517c199713.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20230901%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20230901T001309Z&X-Amz-Expires=300&X-Amz-Signature=72d5d424f2effbadd15e1280b6fb65775222539fd520b17a0d42d140cebba11f&X-Amz-SignedHeaders=host&actor_id=74192619&key_id=0&repo_id=685011150)

4. 내용을 작성 합니다.

![PR 작성](https://github-production-user-asset-6210df.s3.amazonaws.com/74192619/264817860-e2575875-28ed-4f9c-91d7-bb0277069c0f.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20230901%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20230901T000446Z&X-Amz-Expires=300&X-Amz-Signature=f4ba33d573b00844cddecdab5b2c9dfa2824899631d16937a8553da7426d9dbf&X-Amz-SignedHeaders=host&actor_id=74192619&key_id=0&repo_id=685011150)

> 1. 주석은 `<!-- -->`으로 작성할 수 있습니다.
> 2. `#{이슈번호}` 로 관련된 이슈의 제목을 가져올 수 있습니다.
> 3. `{Commit Hash}` 로 관련된 커밋을 가져올 수 있습니다.
> 4. `Reviewers` 로 리뷰를 원하는 인원을 선택할 수 있습니다.
> 5. `Assigness` 로 해당 `PR`에 관여한 인원을 선택할 수 있습니다.

## Branch 전략

![Github Flow](https://subicura.com/git/assets/img/github-flow.2fafce92.png)

1. 브랜치를 생성합니다.
2. 작업을 진행하며 `commit`을 진행합니다.
3. 로컬 저장소에서 변경된 내용을 원격 저장소로 `push` 합니다.
4. 변경된 내용을 가지고 `PR`을 생성합니다.
5. `PR`에서 코드 리뷰를 진행합니다.
6. 코드 리뷰가 끝나고 이상이 없을 때 `Merge` 합니다.
7. 이후 `Main Branch`를 `Pull` 합니다.

## Pull Request

1. 브랜치에서 내용을 수정한 뒤 `Compare & pull request` 를 클릭합니다.

![PR 신청](https://github-production-user-asset-6210df.s3.amazonaws.com/74192619/264817660-12d7ad72-c6a9-44df-9040-3797ebfbc13b.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20230901%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20230901T000250Z&X-Amz-Expires=300&X-Amz-Signature=0ac344f8e2e6b6a1094ba9c9eea823d6e3412eee2e87a0b634a9076a7941488a&X-Amz-SignedHeaders=host&actor_id=74192619&key_id=0&repo_id=685011150)

2. 내용을 작성 합니다.

![PR 작성](https://github-production-user-asset-6210df.s3.amazonaws.com/74192619/264817860-e2575875-28ed-4f9c-91d7-bb0277069c0f.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20230901%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20230901T000446Z&X-Amz-Expires=300&X-Amz-Signature=f4ba33d573b00844cddecdab5b2c9dfa2824899631d16937a8553da7426d9dbf&X-Amz-SignedHeaders=host&actor_id=74192619&key_id=0&repo_id=685011150)

> 1. 주석은 `<!-- -->`으로 작성할 수 있습니다.
> 2. `#{이슈번호}` 로 관련된 이슈의 제목을 가져올 수 있습니다.
> 3. `{Commit Hash}` 로 관련된 커밋을 가져올 수 있습니다.
> 4. `Reviewers` 로 리뷰를 원하는 인원을 선택할 수 있습니다.
> 5. `Assigness` 로 해당 `PR`에 관여한 인원을 선택할 수 있습니다.

